package rtuitlab.orders.controllers;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import rtuitlab.orders.dto.deliveryOrder.*;
import rtuitlab.orders.models.documents.DeliveryOrderDocument;
import rtuitlab.orders.services.DeliveryOrderService;

@RestController
@RequestMapping("/api/v1/delivery_order")
@Api("controller for orders for delivery")
public class DeliveryOrderController extends AbstractController<DeliveryOrderDocument, DeliveryOrderGetDTO, DeliveryOrderPostDTO, DeliveryOrderPutDTO, DeliveryOrderPostedDTO, DeliveryOrderUpdatedDTO, DeliveryOrderService> {
    public DeliveryOrderController(DeliveryOrderService service) {
        super(service);
    }
}
