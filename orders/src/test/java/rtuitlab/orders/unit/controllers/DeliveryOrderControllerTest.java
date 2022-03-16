package rtuitlab.orders.unit.controllers;

import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import rtuitlab.orders.controllers.DeliveryOrderController;
import rtuitlab.orders.dto.deliveryOrder.*;
import rtuitlab.orders.models.BoughtProductInfo;
import rtuitlab.orders.models.documents.DeliveryOrderDocument;
import rtuitlab.orders.services.DeliveryOrderService;

import java.util.Date;
import java.util.List;

public class DeliveryOrderControllerTest extends AbstractControllerTest<DeliveryOrderDocument, DeliveryOrderGetDTO, DeliveryOrderPostDTO, DeliveryOrderPutDTO, DeliveryOrderPostedDTO, DeliveryOrderUpdatedDTO, DeliveryOrderService, DeliveryOrderController> {
    public DeliveryOrderControllerTest() {
        DeliveryOrderService deliveryOrderService = Mockito.mock(DeliveryOrderService.class);
        this.controller = new DeliveryOrderController(deliveryOrderService);
        this.mockService = deliveryOrderService;
        this.eSupplier = () -> new DeliveryOrderDocument(
                "1",
                1,
                300,
                List.of(new BoughtProductInfo(1, 100, 1), new BoughtProductInfo(2, 100, 2)),
                new Date(1),
                "address",
                "phone"
        );
        this.getSupplier = () -> new DeliveryOrderGetDTO(
                "1",
                1,
                300,
                List.of(new BoughtProductInfo(1, 100, 1), new BoughtProductInfo(2, 100, 2)),
                new Date(1),
                "address",
                "phone"
        );
        this.postSupplier = () -> new DeliveryOrderPostDTO (
                List.of(new BoughtProductInfo(1, 100, 1), new BoughtProductInfo(2, 100, 2)),
                "address",
                "phone"
        );
        this.putSupplier = () -> new DeliveryOrderPutDTO(
                List.of(new BoughtProductInfo(1, 100, 1), new BoughtProductInfo(2, 100, 2)),
                new Date(1),
                "address",
                "phone"
        );
        this.postedSupplier = () -> new DeliveryOrderPostedDTO(
                "1",
                1,
                300,
                List.of(new BoughtProductInfo(1, 100, 1), new BoughtProductInfo(2, 100, 2)),
                new Date(1),
                "address",
                "phone"
        );
        this.updatedSupplier = () -> new DeliveryOrderUpdatedDTO(
                "1",
                1,
                300,
                List.of(new BoughtProductInfo(1, 100, 1), new BoughtProductInfo(2, 100, 2)),
                new Date(1),
                "address",
                "phone"
        );
        this.postArgumentCaptorSupplier = () -> ArgumentCaptor.forClass(DeliveryOrderPostDTO.class);
        this.putArgumentCaptorSupplier = () -> ArgumentCaptor.forClass(DeliveryOrderPutDTO.class);
    }
}
