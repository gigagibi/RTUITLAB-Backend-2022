package rtuitlab.orders.unit.mappers;

import rtuitlab.orders.dto.deliveryOrder.*;
import rtuitlab.orders.mappers.DeliveryOrderMapper;
import rtuitlab.orders.mappers.DeliveryOrderMapperImpl;
import rtuitlab.orders.models.BoughtProductInfo;
import rtuitlab.orders.models.documents.DeliveryOrderDocument;

import java.util.Date;
import java.util.List;

public class DeliveryOrderMapperTest extends AbstractMapperTest<DeliveryOrderDocument, DeliveryOrderGetDTO, DeliveryOrderPostDTO, DeliveryOrderPutDTO, DeliveryOrderPostedDTO, DeliveryOrderUpdatedDTO, DeliveryOrderMapper> {
    public DeliveryOrderMapperTest() {
        this.mapper = new DeliveryOrderMapperImpl();
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
    }
}
