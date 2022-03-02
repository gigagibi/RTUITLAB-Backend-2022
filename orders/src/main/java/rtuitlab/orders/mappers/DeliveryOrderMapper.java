package rtuitlab.orders.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import rtuitlab.orders.dto.deliveryOrder.*;
import rtuitlab.orders.models.DeliveryOrder;

@Component
@Mapper(componentModel = "spring")
public interface DeliveryOrderMapper {
    DeliveryOrder postDTOToEntity(PostDeliveryOrderDTO postDeliveryOrderDTO);

    GetDeliveryOrderDTO entityToDTO(DeliveryOrder deliveryOrder);

    DeliveryOrder putDTOToEntity(PutDeliveryOrderDTO putDeliveryOrderDTO);
}
