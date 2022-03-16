package rtuitlab.orders.unit.services;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;
import rtuitlab.orders.dto.deliveryOrder.*;
import rtuitlab.orders.exceptions.EntityNotFoundException;
import rtuitlab.orders.mappers.DeliveryOrderMapper;
import rtuitlab.orders.models.BoughtProductInfo;
import rtuitlab.orders.models.documents.DeliveryOrderDocument;
import rtuitlab.orders.repositories.DeliveryOrderRepository;
import rtuitlab.orders.services.DeliveryOrderService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class DeliveryOrderServiceTest extends AbstractServiceTest<DeliveryOrderDocument, DeliveryOrderGetDTO, DeliveryOrderPostDTO, DeliveryOrderPutDTO, DeliveryOrderPostedDTO, DeliveryOrderUpdatedDTO, DeliveryOrderService, DeliveryOrderMapper, DeliveryOrderRepository> {
    private final RestTemplate mockRestTemplate;
    public DeliveryOrderServiceTest() {
        DeliveryOrderRepository deliveryOrderMockRepository = Mockito.mock(DeliveryOrderRepository.class);
        DeliveryOrderMapper deliveryOrderMockMapper = Mockito.mock(DeliveryOrderMapper.class);
        this.mockRepository = deliveryOrderMockRepository;
        this.mockMapper = deliveryOrderMockMapper;
        this.mockRestTemplate = Mockito.mock(RestTemplate.class);
        this.service = new DeliveryOrderService(deliveryOrderMockRepository, deliveryOrderMockMapper, mockRestTemplate);
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
        this.eArgumentCaptorSupplier = () -> ArgumentCaptor.forClass(DeliveryOrderDocument.class);
    }

    @Test
    void shouldCorrectlyComputeCost_WhenCreatingDeliveryOrder()  {
        // arrange
         DeliveryOrderDocument deliveryOrderDocument = eSupplier.get();
         DeliveryOrderDocument expectedDeliveryOrderDocument = eSupplier.get();
         deliveryOrderDocument.setCost(null);
         DeliveryOrderPostDTO deliveryOrderPostDTO = postSupplier.get();
         given(mockMapper.postDTOToEntity(deliveryOrderPostDTO)).willReturn(deliveryOrderDocument);

         // act
        service.create(deliveryOrderPostDTO);

        // assert
        ArgumentCaptor<DeliveryOrderDocument> eArgumentCaptor = eArgumentCaptorSupplier.get();
        verify(mockRepository).save(eArgumentCaptor.capture());
        DeliveryOrderDocument captured = eArgumentCaptor.getValue();
        assertThat(captured.getCost()).isEqualTo(expectedDeliveryOrderDocument.getCost());
    }

    @Test
    void shouldCorrectlyComputeCost_WhenUpdatingDeliveryOrder() throws EntityNotFoundException {
        // arrange
        DeliveryOrderDocument deliveryOrderDocument = eSupplier.get();
        String id = deliveryOrderDocument.getId();
        DeliveryOrderDocument expectedDeliveryOrderDocument = eSupplier.get();
        deliveryOrderDocument.setCost(null);
        DeliveryOrderPutDTO deliveryOrderPutDTO = putSupplier.get();
        given(mockRepository.existsById(id)).willReturn(true);
        given(mockRepository.findById(id)).willReturn(Optional.of(deliveryOrderDocument));
        given(mockMapper.putDTOToEntity(deliveryOrderPutDTO)).willReturn(deliveryOrderDocument);

        // act
        service.update(id, deliveryOrderPutDTO);

        // assert
        ArgumentCaptor<DeliveryOrderDocument> eArgumentCaptor = eArgumentCaptorSupplier.get();
        verify(mockRepository).save(eArgumentCaptor.capture());
        DeliveryOrderDocument captured = eArgumentCaptor.getValue();
        assertThat(captured.getCost()).isEqualTo(expectedDeliveryOrderDocument.getCost());
    }
}
