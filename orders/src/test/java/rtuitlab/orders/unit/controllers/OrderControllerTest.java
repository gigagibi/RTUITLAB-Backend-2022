package rtuitlab.orders.unit.controllers;

import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import rtuitlab.orders.controllers.OrderController;
import rtuitlab.orders.dto.order.*;
import rtuitlab.orders.models.BoughtProductInfo;
import rtuitlab.orders.models.documents.OrderDocument;
import rtuitlab.orders.services.OrderService;

import java.util.Date;
import java.util.List;

public class OrderControllerTest extends AbstractControllerTest<OrderDocument, OrderGetDTO, OrderPostDTO, OrderPutDTO, OrderPostedDTO, OrderUpdatedDTO, OrderService, OrderController> {
    public OrderControllerTest() {
        OrderService orderService = Mockito.mock(OrderService.class);
        this.controller = new OrderController(orderService);
        this.mockService = orderService;
        this.eSupplier = () -> new OrderDocument(
                "1",
                1,
                300,
                List.of(new BoughtProductInfo(1, 100, 1), new BoughtProductInfo(2, 100, 2)),
                new Date(1)
        );
        this.getSupplier = () -> new OrderGetDTO(
                "1",
                1,
                300,
                List.of(new BoughtProductInfo(1, 100, 1), new BoughtProductInfo(2, 100, 2)),
                new Date(1)
        );
        this.postSupplier = () -> new OrderPostDTO(
                1,
                List.of(new BoughtProductInfo(1, 100, 1), new BoughtProductInfo(2, 100, 2))
        );
        this.putSupplier = () -> new OrderPutDTO(
                1,
                List.of(new BoughtProductInfo(1, 100, 1), new BoughtProductInfo(2, 100, 2)),
                new Date(1)
        );
        this.postedSupplier = () -> new OrderPostedDTO(
                "1",
                1,
                300,
                List.of(new BoughtProductInfo(1, 100, 1), new BoughtProductInfo(2, 100, 2)),
                new Date(1)
        );
        this.updatedSupplier = () -> new OrderUpdatedDTO(
                "1",
                1,
                300,
                List.of(new BoughtProductInfo(1, 100, 1), new BoughtProductInfo(2, 100, 2)),
                new Date(1)
        );
        this.postArgumentCaptorSupplier = () -> ArgumentCaptor.forClass(OrderPostDTO.class);
        this.putArgumentCaptorSupplier = () -> ArgumentCaptor.forClass(OrderPutDTO.class);
    }
}
