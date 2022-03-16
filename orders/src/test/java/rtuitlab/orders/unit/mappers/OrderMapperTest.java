package rtuitlab.orders.unit.mappers;

import rtuitlab.orders.dto.order.*;
import rtuitlab.orders.mappers.OrderMapper;
import rtuitlab.orders.mappers.OrderMapperImpl;
import rtuitlab.orders.models.BoughtProductInfo;
import rtuitlab.orders.models.documents.OrderDocument;

import java.util.Date;
import java.util.List;

public class OrderMapperTest extends AbstractMapperTest<OrderDocument, OrderGetDTO, OrderPostDTO, OrderPutDTO, OrderPostedDTO, OrderUpdatedDTO, OrderMapper> {
    public OrderMapperTest() {
        this.mapper = new OrderMapperImpl();
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
        this.postSupplier = () -> new OrderPostDTO (
                List.of(new BoughtProductInfo(1, 100, 1), new BoughtProductInfo(2, 100, 2))
        );
        this.putSupplier = () -> new OrderPutDTO(
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
    }
}
