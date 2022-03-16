package rtuitlab.orders.services;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import rtuitlab.orders.dto.deliveryOrder.*;
import rtuitlab.orders.dto.rabbit.DeliveryOrderFromDeliveriesDTO;
import rtuitlab.orders.dto.rabbit.DeliveryOrderToDeliveriesDTO;
import rtuitlab.orders.exceptions.EntityNotFoundException;
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

    public DeliveryOrderService(DeliveryOrderRepository repository, @Qualifier("deliveryOrderMapperImpl") DeliveryOrderMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public List<DeliveryOrderPostedDTO> create(DeliveryOrderPostDTO deliveryOrderPostDTO) {
        DeliveryOrderDocument deliveryOrderDocument = mapper.postDTOToEntity(deliveryOrderPostDTO);
        int cost = 0;
        for (BoughtProductInfo boughtProductInfo: deliveryOrderDocument.getProducts()) {
            cost += boughtProductInfo.getCost() * boughtProductInfo.getAmount();
        }
        deliveryOrderDocument.setCost(cost);
        deliveryOrderDocument.setOrderDate(new Date());
        repository.save(deliveryOrderDocument);
        return repository.findAll().stream().map(mapper::entityToPostedDTO).collect(Collectors.toList());
    }

    @Override
    public DeliveryOrderUpdatedDTO update(String id, DeliveryOrderPutDTO deliveryOrderPutDTO) throws EntityNotFoundException {
        if(!repository.existsById(id))
            throw new EntityNotFoundException(id);
        DeliveryOrderDocument deliveryOrderDocument = mapper.putDTOToEntity(deliveryOrderPutDTO);
        deliveryOrderDocument.setId(id);
        int cost = 0;
        for (BoughtProductInfo boughtProductInfo: deliveryOrderDocument.getProducts()) {
            cost += boughtProductInfo.getCost() * boughtProductInfo.getAmount();
        }
        deliveryOrderDocument.setCost(cost);
        repository.save(deliveryOrderDocument);
        DeliveryOrderDocument updatedDeliveryOrderDocument = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
        return mapper.entityToUpdatedDTO(updatedDeliveryOrderDocument);
    }

    @RabbitListener(queues = "deliveries-orders-post-queue")
    public DeliveryOrderToDeliveriesDTO produceDeliveryRequest(DeliveryOrderFromDeliveriesDTO deliveryOrderFromDeliveriesDTO) {
        DeliveryOrderDocument deliveryOrderDocument = mapper.deliveryOrderFromDeliveriesToEntity(deliveryOrderFromDeliveriesDTO);
        repository.save(deliveryOrderDocument);
        return mapper.entityToDeliveryOrderToDeliveries(deliveryOrderDocument);
    }
}