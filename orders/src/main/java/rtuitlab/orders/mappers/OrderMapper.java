package rtuitlab.orders.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import rtuitlab.orders.dto.order.*;
import rtuitlab.orders.models.OrderEntity;

@Component
@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderEntity postDTOToEntity(PostOrderDTO postOrderDTO);

    GetOrderDTO entityToDTO(OrderEntity orderEntity);

    OrderEntity putDTOToEntity(PutOrderDTO putOrderDTO);
}
