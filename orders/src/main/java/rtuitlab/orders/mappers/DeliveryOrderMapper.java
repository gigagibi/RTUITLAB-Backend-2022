package rtuitlab.orders.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import rtuitlab.orders.dto.deliveryOrder.*;
import rtuitlab.orders.dto.deliveryOrder.rabbit.DeliveryOrderFromDeliveriesRabbitDTO;
import rtuitlab.orders.dto.deliveryOrder.rabbit.DeliveryOrderToDeliveriesRabbitDTO;
import rtuitlab.orders.models.documents.DeliveryOrderDocument;

@Component
@Mapper(componentModel = "spring")
public interface DeliveryOrderMapper extends CommonMapper<DeliveryOrderDocument, DeliveryOrderGetDTO, DeliveryOrderPostDTO, DeliveryOrderPutDTO, DeliveryOrderPostedDTO, DeliveryOrderUpdatedDTO> {
    DeliveryOrderDocument deliveryOrderFromDeliveriesToEntity(DeliveryOrderFromDeliveriesRabbitDTO deliveryOrderFromDeliveriesRabbitDTO);
    DeliveryOrderToDeliveriesRabbitDTO entityToDeliveryOrderToDeliveries(DeliveryOrderDocument deliveryOrderDocument);
}