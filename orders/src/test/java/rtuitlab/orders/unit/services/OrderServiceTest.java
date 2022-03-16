package rtuitlab.orders.unit.services;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;
import rtuitlab.orders.dto.order.OrderPostDTO;
import rtuitlab.orders.dto.order.OrderPutDTO;
import rtuitlab.orders.dto.order.*;
import rtuitlab.orders.exceptions.EntityNotFoundException;
import rtuitlab.orders.mappers.OrderMapper;
import rtuitlab.orders.models.BoughtProductInfo;
import rtuitlab.orders.models.documents.OrderDocument;
import rtuitlab.orders.repositories.OrderRepository;
import rtuitlab.orders.services.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class OrderServiceTest extends AbstractServiceTest<OrderDocument, OrderGetDTO, OrderPostDTO, OrderPutDTO, OrderPostedDTO, OrderUpdatedDTO, OrderService, OrderMapper, OrderRepository> {
    private final RestTemplate mockRestTemplate;
    public OrderServiceTest() {
        OrderRepository OrderMockRepository = Mockito.mock(OrderRepository.class);
        OrderMapper OrderMockMapper = Mockito.mock(OrderMapper.class);
        this.mockRestTemplate = Mockito.mock(RestTemplate.class);
        this.mockRepository = OrderMockRepository;
        this.mockMapper = OrderMockMapper;
        this.service = new OrderService(OrderMockRepository, OrderMockMapper, this.mockRestTemplate);
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
        this.eArgumentCaptorSupplier = () -> ArgumentCaptor.forClass(OrderDocument.class);
    }
    @Test
    void shouldCorrectlyComputeCost_WhenCreatingOrder() {
        // arrange
        OrderDocument OrderDocument = eSupplier.get();
        OrderDocument expectedOrderDocument = eSupplier.get();
        OrderDocument.setCost(null);
        OrderPostDTO OrderPostDTO = postSupplier.get();
        given(mockMapper.postDTOToEntity(OrderPostDTO)).willReturn(OrderDocument);

        // act
        service.create(OrderPostDTO);

        // assert
        ArgumentCaptor<OrderDocument> eArgumentCaptor = eArgumentCaptorSupplier.get();
        verify(mockRepository).save(eArgumentCaptor.capture());
        OrderDocument captured = eArgumentCaptor.getValue();
        assertThat(captured.getCost()).isEqualTo(expectedOrderDocument.getCost());
    }

    @Test
    void shouldCorrectlyComputeCost_WhenUpdatingOrder() throws EntityNotFoundException {
        // arrange

        OrderDocument OrderDocument = eSupplier.get();
        String id = OrderDocument.getId();
        OrderDocument expectedOrderDocument = eSupplier.get();
        OrderDocument.setCost(null);
        OrderPutDTO OrderPutDTO = putSupplier.get();
        given(mockRepository.existsById(id)).willReturn(true);
        given(mockRepository.findById(id)).willReturn(Optional.of(OrderDocument));
        given(mockMapper.putDTOToEntity(OrderPutDTO)).willReturn(OrderDocument);

        // act
        service.update(id, OrderPutDTO);

        // assert
        ArgumentCaptor<OrderDocument> eArgumentCaptor = eArgumentCaptorSupplier.get();
        verify(mockRepository).save(eArgumentCaptor.capture());
        OrderDocument captured = eArgumentCaptor.getValue();
        assertThat(captured.getCost()).isEqualTo(expectedOrderDocument.getCost());
    }
}
