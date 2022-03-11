package rtuitlab.orders.services.mongoImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rtuitlab.orders.dto.order.PostOrderDTO;
import rtuitlab.orders.dto.order.PutOrderDTO;
import rtuitlab.orders.exceptions.OrderNotFoundException;
import rtuitlab.orders.mappers.OrderMapper;
import rtuitlab.orders.models.BoughtProductInfo;
import rtuitlab.orders.models.documents.OrderDocument;
import rtuitlab.orders.repositories.OrderRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderServiceMongoTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock private OrderMapper orderMapper;
    private OrderServiceMongo underTest;

    private OrderDocument testOrderDocument;
    private OrderDocument testUpdatedOrderDocument;
    private PostOrderDTO testPostOrderDTO;
    private PutOrderDTO testPutOrderDTO;

    @BeforeEach
    void setUp() {
        underTest = new OrderServiceMongo(orderRepository, orderMapper);
        List<BoughtProductInfo> boughtProductInfos = Arrays.asList(
                new BoughtProductInfo(1, 100, 1),
                new BoughtProductInfo(2, 200, 1));
        List<BoughtProductInfo> updatedBoughtProductInfos = Arrays.asList(
                new BoughtProductInfo(1, 100, 1),
                new BoughtProductInfo(2, 200, 1));

        Date date = new Date();
        testOrderDocument = new OrderDocument(
                "1",
                1,
                100,
                boughtProductInfos,
                date
        );

        testPostOrderDTO = new PostOrderDTO(1,
                boughtProductInfos);
        testPutOrderDTO = new PutOrderDTO(2,
                updatedBoughtProductInfos, date);
        testUpdatedOrderDocument = new OrderDocument(null, 2, 150,
                updatedBoughtProductInfos, date);
    }

    @Test
    void getAll() {
        // arrange (no given arrange)

        // act
        underTest.getAll();

        // assert
        verify(orderRepository).findAll();
//        verify(orderMapper, atLeastOnce()).entityToDTO(any()); dont work because method is invoked in stream. Need to figure it out
    }

    @Test
    void shouldGetOrderById() throws OrderNotFoundException {
        // arrange
        String id = testOrderDocument.getId();
        given(orderRepository.findById(id))
                .willReturn(Optional.of(testOrderDocument));

        // act
        underTest.getById(id);

        // assert
        ArgumentCaptor<String> idArgumentCaptor
                = ArgumentCaptor.forClass(String.class);
        verify(orderRepository)
                .findById(idArgumentCaptor.capture());
        String capturedId = idArgumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);
        verify(orderMapper).entityToDTO(any());
    }

    @Test
    void shouldCreateOrder() {
        // arrange
        given(orderMapper.postDTOToEntity(any()))
                .willReturn(testOrderDocument);

        // act
        underTest.create(testPostOrderDTO);

        // assert
        ArgumentCaptor<OrderDocument> orderDocumentArgumentCaptor
                = ArgumentCaptor.forClass(OrderDocument.class);
        verify(orderRepository)
                .save(orderDocumentArgumentCaptor.capture());
        OrderDocument orderDocument
                = orderDocumentArgumentCaptor.getValue();
        assertThat(orderDocument.getNumber()).isEqualTo(testPostOrderDTO.getNumber());
    }

    @Test
    void shouldDeleteOrderById() throws OrderNotFoundException {
        // arrange
        String expectedId = testOrderDocument.getId();
        given(orderRepository.findById(expectedId))
                .willReturn(Optional.of(testOrderDocument));

        // act
        underTest.deleteById(expectedId);

        // assert
        ArgumentCaptor<String> idArgumentCaptor
                = ArgumentCaptor.forClass(String.class);
        verify(orderRepository)
                .deleteById(idArgumentCaptor.capture());
//        verify(orderMapper) don't work because method is invoked in stream. Need to figure it out
//                .entityToDTO(any());
        String id = idArgumentCaptor.getValue();
        assertThat(expectedId).isEqualTo(id);
    }

    @Test
    void shouldUpdateOrderById() throws OrderNotFoundException {
        // arrange
        String id = testOrderDocument.getId();
        given(orderMapper.putDTOToEntity(testPutOrderDTO))
                .willReturn(testUpdatedOrderDocument);
        given(orderRepository.findById(id))
                .willReturn(Optional.of(testOrderDocument));

        // act
        underTest.update(id, testPutOrderDTO);

        // assert
        ArgumentCaptor<OrderDocument> orderDocumentArgumentCaptor
                = ArgumentCaptor.forClass(OrderDocument.class);
        verify(orderRepository)
                .save(orderDocumentArgumentCaptor.capture());
        OrderDocument orderDocument
                = orderDocumentArgumentCaptor.getValue();
        assertThat(orderDocument.getId()).isEqualTo(id);
        assertThat(orderDocument.getNumber()).isEqualTo(2);
    }
}