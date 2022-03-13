package rtuitlab.orders.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import rtuitlab.orders.dto.deliveryOrder.*;
import rtuitlab.orders.models.documents.DeliveryOrderDocument;
import rtuitlab.orders.services.DeliveryOrderService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/delivery_order")
@Api("controller for orders for delivery")
public class DeliveryController extends AbstractController<DeliveryOrderDocument, DeliveryOrderGetDTO, DeliveryOrderPostDTO, DeliveryOrderPutDTO, DeliveryOrderPostedDTO, DeliveryOrderUpdatedDTO, DeliveryOrderService> {
    public DeliveryController(DeliveryOrderService service) {
        super(service);
    }
}
