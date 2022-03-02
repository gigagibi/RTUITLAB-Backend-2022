package rtuitlab.orders.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import rtuitlab.orders.dto.order.*;
import rtuitlab.orders.models.Order;

@Component
@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order postDTOToEntity(PostOrderDTO postOrderDTO);

    GetOrderDTO entityToDTO(Order order);

    Order putDTOToEntity(PutOrderDTO putOrderDTO);
}
