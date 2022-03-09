package rtuitlab.orders.services.mongoImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rtuitlab.orders.dto.deliveryOrder.PostDeliveryOrderDTO;
import rtuitlab.orders.dto.deliveryOrder.PutDeliveryOrderDTO;
import rtuitlab.orders.exceptions.DeliveryOrderNotFoundException;
import rtuitlab.orders.mappers.DeliveryOrderMapper;
import rtuitlab.orders.models.BoughtProductInfo;
import rtuitlab.orders.models.documents.DeliveryOrderDocument;
import rtuitlab.orders.repositories.DeliveryOrderRepository;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith({MockitoExtension.class})
class DeliveryOrderServiceMongoTest {

    @Mock private DeliveryOrderRepository deliveryOrderRepository;
    @Mock private DeliveryOrderMapper deliveryOrderMapper;
    private DeliveryOrderServiceMongo underTest;

    private DeliveryOrderDocument testDeliveryOrderDocument;
    private DeliveryOrderDocument testUpdatedDeliveryOrderDocument;
    private PostDeliveryOrderDTO testPostDeliveryOrderDTO;
    private PutDeliveryOrderDTO testPutDeliveryOrderDTO;

    @BeforeEach
    void setUp() {
        underTest = new DeliveryOrderServiceMongo(deliveryOrderRepository, deliveryOrderMapper);
        List<BoughtProductInfo> boughtProductInfos = Arrays.asList(
                new BoughtProductInfo(1, 1),
                new BoughtProductInfo(2, 1));
        List<BoughtProductInfo> updatedBoughtProductInfos = Arrays.asList(
                new BoughtProductInfo(1, 1),
                new BoughtProductInfo(2, 1));

        Date date = new Date();
        testDeliveryOrderDocument = new DeliveryOrderDocument(
                "1",
                1,
                100,
                boughtProductInfos,
                date,
                "address",
                "phone"
        );

        testPostDeliveryOrderDTO = new PostDeliveryOrderDTO(1, 100,
                boughtProductInfos, "address", "phone");
        testPutDeliveryOrderDTO = new PutDeliveryOrderDTO(2, 150,
                updatedBoughtProductInfos, date, "address_changed", "phone_changed");
        testUpdatedDeliveryOrderDocument = new DeliveryOrderDocument(null, 2, 150,
                updatedBoughtProductInfos, date, "address_changed", "phone_changed");
    }

    @Test
    void getAll() {
        underTest.getAll();
        verify(deliveryOrderRepository).findAll();
//        verify(deliveryOrderMapper, atLeastOnce()).entityToDTO(any()); dont work because method is invoked in stream. Need to figure it out
    }

    @Test
    void shouldGetDeliveryOrderById() throws DeliveryOrderNotFoundException {
        // arrange
        String id = testDeliveryOrderDocument.getId();
        given(deliveryOrderRepository.findById(id))
                .willReturn(Optional.of(testDeliveryOrderDocument));

        // act
        underTest.getById(id);

        // assert
        ArgumentCaptor<String> idArgumentCaptor
                = ArgumentCaptor.forClass(String.class);
        verify(deliveryOrderRepository)
                .findById(idArgumentCaptor.capture());
        String capturedId = idArgumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);
        verify(deliveryOrderMapper).entityToDTO(any());
    }

    @Test
    void shouldCreateDeliveryOrder() {
        // arrange
        given(deliveryOrderMapper.postDTOToEntity(any()))
                .willReturn(testDeliveryOrderDocument);

        // act
        underTest.create(testPostDeliveryOrderDTO);

        // assert
        ArgumentCaptor<DeliveryOrderDocument> deliveryOrderDocumentArgumentCaptor
                = ArgumentCaptor.forClass(DeliveryOrderDocument.class);
        verify(deliveryOrderRepository)
                .save(deliveryOrderDocumentArgumentCaptor.capture());
        DeliveryOrderDocument deliveryOrderDocument
                = deliveryOrderDocumentArgumentCaptor.getValue();
        assertThat(deliveryOrderDocument.getNumber()).isEqualTo(testPostDeliveryOrderDTO.getNumber());
    }

    @Test
    void shouldDeleteDeliveryOrderById() throws DeliveryOrderNotFoundException {
        // arrange
        String expectedId = testDeliveryOrderDocument.getId();
        given(deliveryOrderRepository.findById(expectedId))
                .willReturn(Optional.of(testDeliveryOrderDocument));

        // act
        underTest.deleteById(expectedId);

        // assert
        ArgumentCaptor<String> idArgumentCaptor
                = ArgumentCaptor.forClass(String.class);
        verify(deliveryOrderRepository)
                .deleteById(idArgumentCaptor.capture());
//        verify(deliveryOrderMapper) don't work because method is invoked in stream. Need to figure it out
//                .entityToDTO(any());
        String id = idArgumentCaptor.getValue();
        assertThat(expectedId).isEqualTo(id);
    }

    @Test
    void shouldUpdateDeliveryOrderById() throws DeliveryOrderNotFoundException {
        // arrange
        String id = testDeliveryOrderDocument.getId();
        given(deliveryOrderMapper.putDTOToEntity(testPutDeliveryOrderDTO))
                .willReturn(testUpdatedDeliveryOrderDocument);
        given(deliveryOrderRepository.findById(id))
                .willReturn(Optional.of(testDeliveryOrderDocument));

        // act
        underTest.update(id, testPutDeliveryOrderDTO);

        // assert
        ArgumentCaptor<DeliveryOrderDocument> deliveryOrderDocumentArgumentCaptor
                = ArgumentCaptor.forClass(DeliveryOrderDocument.class);
        verify(deliveryOrderRepository)
                .save(deliveryOrderDocumentArgumentCaptor.capture());
        DeliveryOrderDocument deliveryOrderDocument
                = deliveryOrderDocumentArgumentCaptor.getValue();
        assertThat(deliveryOrderDocument.getId()).isEqualTo(id);
        assertThat(deliveryOrderDocument.getNumber()).isEqualTo(2);
    }
}