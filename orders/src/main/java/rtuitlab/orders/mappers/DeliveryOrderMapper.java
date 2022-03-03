package rtuitlab.orders.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import rtuitlab.orders.dto.deliveryOrder.*;
import rtuitlab.orders.models.DeliveryOrderEntity;

@Component
@Mapper(componentModel = "spring")
public interface DeliveryOrderMapper {
    DeliveryOrderEntity postDTOToEntity(PostDeliveryOrderDTO postDeliveryOrderDTO);

    GetDeliveryOrderDTO entityToDTO(DeliveryOrderEntity deliveryOrderEntity);

    DeliveryOrderEntity putDTOToEntity(PutDeliveryOrderDTO putDeliveryOrderDTO);
}
