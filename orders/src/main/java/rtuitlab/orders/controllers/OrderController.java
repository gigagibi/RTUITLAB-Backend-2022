package rtuitlab.orders.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import rtuitlab.orders.dto.order.*;
import rtuitlab.orders.models.documents.OrderDocument;
import rtuitlab.orders.services.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@Api("controller for orders")
public class OrderController extends AbstractController<OrderDocument, OrderGetDTO, OrderPostDTO, OrderPutDTO, OrderPostedDTO, OrderUpdatedDTO, OrderService> {

    public OrderController(OrderService service) {
        super(service);
    }
}
