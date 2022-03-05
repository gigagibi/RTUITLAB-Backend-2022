package rtuitlab.orders.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import rtuitlab.orders.models.documents.DeliveryOrderDocument;
import rtuitlab.orders.dto.deliveryOrder.GetDeliveryOrderDTO;
import rtuitlab.orders.dto.deliveryOrder.PostDeliveryOrderDTO;
import rtuitlab.orders.dto.deliveryOrder.PutDeliveryOrderDTO;

@Component
@Mapper(componentModel = "spring")
public interface DeliveryOrderMapper {
    DeliveryOrderDocument postDTOToEntity(PostDeliveryOrderDTO postDeliveryOrderDTO);

    GetDeliveryOrderDTO entityToDTO(DeliveryOrderDocument deliveryOrderDocument);

    DeliveryOrderDocument putDTOToEntity(PutDeliveryOrderDTO putDeliveryOrderDTO);
}
