package rtuitlab.orders.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import rtuitlab.orders.dto.order.*;
import rtuitlab.orders.models.documents.OrderDocument;

@Component
@Mapper(componentModel = "spring")
public interface OrderMapper extends CommonMapper<OrderDocument, OrderGetDTO, OrderPostDTO, OrderPutDTO, OrderPostedDTO, OrderUpdatedDTO> {
}
