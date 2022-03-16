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
import rtuitlab.orders.exceptions.EntityNotFoundException;
import rtuitlab.orders.exceptions.ProductToDeliveriesNotFound;
import rtuitlab.orders.models.BoughtProductInfo;
import rtuitlab.orders.models.documents.DeliveryOrderDocument;
import rtuitlab.orders.repositories.DeliveryOrderRepository;
import rtuitlab.orders.mappers.DeliveryOrderMapper;

import java.util.Date;
import java.util.List;
import java.util.Optional;
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
    public List<DeliveryOrderPostedDTO> create(DeliveryOrderPostDTO deliveryOrderPostDTO) {
        DeliveryOrderDocument deliveryOrderDocument = mapper.postDTOToEntity(deliveryOrderPostDTO);
        int summaryCost = 0;
        for (BoughtProductInfo boughtProductInfo: deliveryOrderDocument.getProducts()) {
            int productId = boughtProductInfo.getId();
            ResponseEntity<Integer> productCostEntity = restTemplate.getForEntity(
                    "http://products/api/v1/products/" + productId + "/cost",
                    Integer.class);
            if (productCostEntity.getStatusCode()==HttpStatus.NOT_FOUND)
                summaryCost += boughtProductInfo.getCost() * boughtProductInfo.getAmount();
            else {
                boughtProductInfo.setCost(productCostEntity.getBody());
                summaryCost += productCostEntity.getBody() * boughtProductInfo.getAmount();
            }
        }
        deliveryOrderDocument.setCost(summaryCost);
        deliveryOrderDocument.setOrderDate(new Date());
        deliveryOrderDocument.setNumber(findMaxOrderNumber()+1);
        repository.save(deliveryOrderDocument);
        return repository.findAll().stream().map(mapper::entityToPostedDTO).collect(Collectors.toList());
    }

    @Override
    public DeliveryOrderUpdatedDTO update(String id, DeliveryOrderPutDTO deliveryOrderPutDTO) throws EntityNotFoundException {
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
                summaryCost += boughtProductInfo.getCost() * boughtProductInfo.getAmount();
            else {
                boughtProductInfo.setCost(productCostEntity.getBody());
                summaryCost += productCostEntity.getBody() * boughtProductInfo.getAmount();
            }
        }

        deliveryOrderDocument.setCost(summaryCost);
        deliveryOrderDocument.setNumber(getNumberOfOrderById(id));
        repository.save(deliveryOrderDocument);
        DeliveryOrderDocument updatedDeliveryOrderDocument = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
        return mapper.entityToUpdatedDTO(updatedDeliveryOrderDocument);
    }

    @RabbitListener(queues = "deliveries-orders-post-queue")
    public DeliveryOrderToDeliveriesRabbitDTO produceDeliveryRequest(DeliveryOrderFromDeliveriesRabbitDTO deliveryOrderFromDeliveriesRabbitDTO) throws ProductToDeliveriesNotFound {
        int summaryCost = 0;
        for (BoughtProductInfo boughtProductInfo : deliveryOrderFromDeliveriesRabbitDTO.getProducts()) {
            int productId = boughtProductInfo.getId();
            ResponseEntity<Integer> productCostEntity = restTemplate.getForEntity(
                    "http://products/api/v1/products/" + productId + "/cost",
                    Integer.class);
            if(productCostEntity.getStatusCode()== HttpStatus.NOT_FOUND)
                throw new ProductToDeliveriesNotFound(productId);
            boughtProductInfo.setCost(productCostEntity.getBody());
            summaryCost += productCostEntity.getBody() * boughtProductInfo.getAmount();
        }
        DeliveryOrderDocument deliveryOrderDocument = mapper.deliveryOrderFromDeliveriesToEntity(deliveryOrderFromDeliveriesRabbitDTO);
        deliveryOrderDocument.setCost(summaryCost);
        deliveryOrderDocument.setOrderDate(new Date());
        deliveryOrderDocument.setNumber(findMaxOrderNumber()+1);
        repository.save(deliveryOrderDocument);
        return mapper.entityToDeliveryOrderToDeliveries(deliveryOrderDocument);
    }

    private int findMaxOrderNumber() {
        List<DeliveryOrderDocument> orders = repository.findAll();
        Optional<DeliveryOrderDocument> optional = orders.stream().reduce((a, b) -> a.getNumber() > b.getNumber() ? a : b);
        if(optional.isPresent())
            return optional.get().getNumber();
        else
            return 0;
    }

    private int getNumberOfOrderById(String id) throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id)).getNumber();
    }

}