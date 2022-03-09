package rtuitlab.orders.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import rtuitlab.orders.models.documents.OrderDocument;
import rtuitlab.orders.dto.order.GetOrderDTO;
import rtuitlab.orders.dto.order.PostOrderDTO;
import rtuitlab.orders.dto.order.PutOrderDTO;

@Component
@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDocument postDTOToEntity(PostOrderDTO postOrderDTO);

    GetOrderDTO entityToDTO(OrderDocument orderDocument);

    OrderDocument putDTOToEntity(PutOrderDTO putOrderDTO);
}
