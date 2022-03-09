package rtuitlab.orders.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rtuitlab.orders.dto.order.GetOrderDTO;
import rtuitlab.orders.dto.order.PostOrderDTO;
import rtuitlab.orders.dto.order.PutOrderDTO;
import rtuitlab.orders.models.BoughtProductInfo;
import rtuitlab.orders.models.documents.OrderDocument;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class OrderMapperTest {
    private OrderMapperImpl underTest;

    private OrderDocument testOrderDocument;
    private PostOrderDTO testPostOrderDTO;
    private PutOrderDTO testPutOrderDTO;

    @BeforeEach
    void setUp() {
        underTest = new OrderMapperImpl();
        List<BoughtProductInfo> boughtProductInfos = Arrays.asList(
                new BoughtProductInfo(1, 1),
                new BoughtProductInfo(2, 1));
        List<BoughtProductInfo> updatedBoughtProductInfos = Arrays.asList(
                new BoughtProductInfo(1, 2),
                new BoughtProductInfo(2, 2));
        Date date = new Date();
        testOrderDocument = new OrderDocument(
                "1",
                1,
                100,
                boughtProductInfos,
                date
        );
        testPostOrderDTO = new PostOrderDTO(1, 100,
                boughtProductInfos);
        testPutOrderDTO = new PutOrderDTO(2, 150,
                updatedBoughtProductInfos, date);
    }

    @Test
    void shouldConvertPostDTOToEntity() {
        // arrange (no given arrange)

        // act
        OrderDocument orderDocument = underTest.postDTOToEntity(testPostOrderDTO);

        // assert
        assertThat(orderDocument.getNumber()).isEqualTo(testPostOrderDTO.getNumber());
    }

    @Test
    void shouldConvertEntityToGetDTO() {
        // arrange (no given arrange)

        // act
        GetOrderDTO getOrderDTO = underTest.entityToDTO(testOrderDocument);

        // assert
        assertThat(getOrderDTO.getId()).isEqualTo(testOrderDocument.getId());
    }

    @Test
    void shouldConvertPutDTOToEntity() {
        OrderDocument orderDocument = underTest.putDTOToEntity(testPutOrderDTO);

        // assert
        assertThat(orderDocument.getNumber()).isEqualTo(testPutOrderDTO.getNumber());
    }
}