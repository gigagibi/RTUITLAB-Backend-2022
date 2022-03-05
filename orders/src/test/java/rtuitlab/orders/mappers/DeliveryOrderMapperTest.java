package rtuitlab.orders.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import rtuitlab.orders.dto.deliveryOrder.GetDeliveryOrderDTO;
import rtuitlab.orders.dto.deliveryOrder.PostDeliveryOrderDTO;
import rtuitlab.orders.dto.deliveryOrder.PutDeliveryOrderDTO;
import rtuitlab.orders.models.BoughtProductInfo;
import rtuitlab.orders.models.documents.DeliveryOrderDocument;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class DeliveryOrderMapperTest {
    private DeliveryOrderMapperImpl underTest;

    private DeliveryOrderDocument testDeliveryOrderDocument;
    private PostDeliveryOrderDTO testPostDeliveryOrderDTO;
    private PutDeliveryOrderDTO testPutDeliveryOrderDTO;

    @BeforeEach
    void setUp() {
        underTest = new DeliveryOrderMapperImpl();
        List<BoughtProductInfo> boughtProductInfos = Arrays.asList(
                new BoughtProductInfo(1, 1),
                new BoughtProductInfo(2, 1));
        List<BoughtProductInfo> updatedBoughtProductInfos = Arrays.asList(
                new BoughtProductInfo(1, 2),
                new BoughtProductInfo(2, 2));
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
    }

    @Test
    void shouldConvertPostDTOToEntity() {
        // arrange (no given arrange)

        // act
        DeliveryOrderDocument deliveryOrderDocument = underTest.postDTOToEntity(testPostDeliveryOrderDTO);

        // assert
        assertThat(deliveryOrderDocument.getNumber()).isEqualTo(testPostDeliveryOrderDTO.getNumber());
        assertThat(deliveryOrderDocument.getAddress()).isEqualTo(testPostDeliveryOrderDTO.getAddress());
    }

    @Test
    void shouldConvertEntityToGetDTO() {
        // arrange (no given arrange)

        // act
        GetDeliveryOrderDTO getDeliveryOrderDTO = underTest.entityToDTO(testDeliveryOrderDocument);

        // assert
        assertThat(getDeliveryOrderDTO.getId()).isEqualTo(testDeliveryOrderDocument.getId());
    }

    @Test
    void shouldConvertPutDTOToEntity() {
        DeliveryOrderDocument deliveryOrderDocument = underTest.putDTOToEntity(testPutDeliveryOrderDTO);

        // assert
        assertThat(deliveryOrderDocument.getNumber()).isEqualTo(testPutDeliveryOrderDTO.getNumber());
        assertThat(deliveryOrderDocument.getAddress()).isEqualTo(testPutDeliveryOrderDTO.getAddress());
    }
}