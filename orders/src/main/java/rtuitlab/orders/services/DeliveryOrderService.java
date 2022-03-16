package rtuitlab.orders.services;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rtuitlab.orders.dto.deliveryOrder.*;
import rtuitlab.orders.dto.deliveryOrder.rabbit.DeliveryOrderFromDeliveriesRabbitDTO;
import rtuitlab.orders.dto.deliveryOrder.rabbit.DeliveryOrderToDeliveriesRabbitDTO;
import rtuitlab.orders.exceptions.EntityCreateErrorException;
import rtuitlab.orders.exceptions.EntityNotFoundException;
import rtuitlab.orders.exceptions.EntityUpdateErrorException;
import rtuitlab.orders.models.BoughtProductInfo;
import rtuitlab.orders.models.documents.DeliveryOrderDocument;
import rtuitlab.orders.repositories.DeliveryOrderRepository;
import rtuitlab.orders.mappers.DeliveryOrderMapper;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableRabbit
public class DeliveryOrderService extends AbstractService<DeliveryOrderDocument, DeliveryOrderRepository, DeliveryOrderGetDTO, DeliveryOrderPostDTO, DeliveryOrderPutDTO, DeliveryOrderPostedDTO, DeliveryOrderUpdatedDTO, DeliveryOrderMapper> {
    private final RestTemplate restTemplate;
    @Autowired
    public DeliveryOrderService(DeliveryOrderRepository repository, @Qualifier("deliveryOrderMapperImpl") DeliveryOrderMapper mapper, RestTemplate restTemplate) {
        super(repository, mapper);
        this.restTemplate = restTemplate;
    }

    @Override
    public List<DeliveryOrderPostedDTO> create(DeliveryOrderPostDTO deliveryOrderPostDTO) throws EntityCreateErrorException {
        DeliveryOrderDocument deliveryOrderDocument = mapper.postDTOToEntity(deliveryOrderPostDTO);
        int summaryCost = 0;
        for (BoughtProductInfo boughtProductInfo: deliveryOrderDocument.getProducts()) {
            int productId = boughtProductInfo.getId();
            ResponseEntity<Integer> productCostEntity = restTemplate.getForEntity(
                    "http://products/api/v1/products/" + productId + "/cost",
                    Integer.class);
            if (productCostEntity.getStatusCode()==HttpStatus.NOT_FOUND)
                throw new EntityCreateErrorException();
            boughtProductInfo.setCost(productCostEntity.getBody());
            summaryCost += productCostEntity.getBody() * boughtProductInfo.getAmount();
        }
        deliveryOrderDocument.setCost(summaryCost);
        deliveryOrderDocument.setOrderDate(new Date());
        repository.save(deliveryOrderDocument);
        return repository.findAll().stream().map(mapper::entityToPostedDTO).collect(Collectors.toList());
    }

    @Override
    public DeliveryOrderUpdatedDTO update(String id, DeliveryOrderPutDTO deliveryOrderPutDTO) throws EntityNotFoundException, EntityUpdateErrorException {
        if(!repository.existsById(id)) {
            throw new EntityNotFoundException(id);
        }
        DeliveryOrderDocument deliveryOrderDocument = mapper.putDTOToEntity(deliveryOrderPutDTO);
        deliveryOrderDocument.setId(id);

        int summaryCost = 0;
        for (BoughtProductInfo boughtProductInfo: deliveryOrderDocument.getProducts()) {
            int productId = boughtProductInfo.getId();
            ResponseEntity<Integer> productCostEntity = restTemplate.getForEntity(
                    "http://products/api/v1/products/" + productId + "/cost",
                    Integer.class);
            if (productCostEntity.getStatusCode()==HttpStatus.NOT_FOUND)
                throw new EntityUpdateErrorException();
            boughtProductInfo.setCost(productCostEntity.getBody());
            summaryCost += productCostEntity.getBody() * boughtProductInfo.getAmount();
        }

        deliveryOrderDocument.setCost(summaryCost);
        repository.save(deliveryOrderDocument);
        DeliveryOrderDocument updatedDeliveryOrderDocument = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
        return mapper.entityToUpdatedDTO(updatedDeliveryOrderDocument);
    }

    @RabbitListener(queues = "deliveries-orders-post-queue")
    public DeliveryOrderToDeliveriesRabbitDTO produceDeliveryRequest(DeliveryOrderFromDeliveriesRabbitDTO deliveryOrderFromDeliveriesRabbitDTO) throws EntityCreateErrorException {
        int summaryCost = 0;
        for (BoughtProductInfo boughtProductInfo : deliveryOrderFromDeliveriesRabbitDTO.getProducts()) {
            int productId = boughtProductInfo.getId();
            ResponseEntity<Integer> productCostEntity = restTemplate.getForEntity(
                    "http://products/api/v1/products/" + productId + "/cost",
                    Integer.class);
            if(productCostEntity.getStatusCode()== HttpStatus.NOT_FOUND)
                throw new EntityCreateErrorException();
            boughtProductInfo.setCost(productCostEntity.getBody());
            summaryCost += productCostEntity.getBody() * boughtProductInfo.getAmount();
        }
        DeliveryOrderDocument deliveryOrderDocument = mapper.deliveryOrderFromDeliveriesToEntity(deliveryOrderFromDeliveriesRabbitDTO);
        deliveryOrderDocument.setCost(summaryCost);
        deliveryOrderDocument.setOrderDate(new Date());
        repository.save(deliveryOrderDocument);
        DeliveryOrderToDeliveriesRabbitDTO dto =mapper.entityToDeliveryOrderToDeliveries(deliveryOrderDocument);
        return dto;
    }
}