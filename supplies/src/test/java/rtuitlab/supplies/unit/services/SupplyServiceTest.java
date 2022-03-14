package rtuitlab.supplies.unit.services;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import rtuitlab.supplies.dto.supply.*;
import rtuitlab.supplies.mappers.SupplyMapper;
import rtuitlab.supplies.models.SupplyProductInfo;
import rtuitlab.supplies.models.documents.SupplyDocument;
import rtuitlab.supplies.repositories.SupplyRepository;
import rtuitlab.supplies.services.SupplyService;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class SupplyServiceTest extends AbstractServiceTest<SupplyDocument, SupplyGetDTO, SupplyPostDTO, SupplyPutDTO, SupplyPostedDTO, SupplyUpdatedDTO, SupplyService, SupplyMapper, SupplyRepository> {
    public SupplyServiceTest() {
        SupplyRepository supplyMockRepository = Mockito.mock(SupplyRepository.class);
        SupplyMapper supplyMockMapper = Mockito.mock(SupplyMapper.class);
        this.mockRepository = supplyMockRepository;
        this.mockMapper = supplyMockMapper;
        this.service = new SupplyService(supplyMockRepository, supplyMockMapper);
        this.eSupplier = () -> new SupplyDocument(
                "1",
                "1",
                new Date(1),
                List.of(new SupplyProductInfo("product", "desc", 1, 100), new SupplyProductInfo("product2", "desc2", 2, 200)),
                500
        );
        this.getSupplier = () -> new SupplyGetDTO(
                "1",
                "1",
                new Date(1),
                List.of(new SupplyProductInfo("product", "desc", 1, 100), new SupplyProductInfo("product2", "desc2", 2, 200)),
                500
        );
        this.postSupplier = () -> new SupplyPostDTO(
                "1",
                new Date(1),
                List.of(new SupplyProductInfo("product", "desc", 1, 100), new SupplyProductInfo("product2", "desc2", 2, 200))
        );
        this.putSupplier = () -> new SupplyPutDTO(
                "1",
                new Date(1),
                List.of(new SupplyProductInfo("product", "desc", 1, 100), new SupplyProductInfo("product2", "desc2", 2, 200))
        );
        this.postedSupplier = () -> new SupplyPostedDTO(
                "1",
                "1",
                new Date(1),
                List.of(new SupplyProductInfo("product", "desc", 1, 100), new SupplyProductInfo("product2", "desc2", 2, 200))
        );
        this.updatedSupplier = () -> new SupplyUpdatedDTO(
                "1",
                "1",
                new Date(1),
                List.of(new SupplyProductInfo("product", "desc", 1, 100), new SupplyProductInfo("product2", "desc2", 2, 200))
        );
        this.eArgumentCaptorSupplier = () -> ArgumentCaptor.forClass(SupplyDocument.class);
    }

    @Test
    void shouldCorrectlyComputeSummaryCost_WhenCreatingSupply() {
        SupplyDocument supplyDocument = eSupplier.get();
        supplyDocument.setSummaryCost(null);
        SupplyDocument expectedSupplyDocument = eSupplier.get();
        SupplyPostDTO supplyPostDTO = postSupplier.get();
        given(mockMapper.postDTOToEntity(supplyPostDTO)).willReturn(supplyDocument);

        // act
        service.create(supplyPostDTO);

        // assert
        ArgumentCaptor<SupplyDocument> eArgumentCaptor = eArgumentCaptorSupplier.get();
        verify(mockRepository).save(eArgumentCaptor.capture());
        SupplyDocument captured = eArgumentCaptor.getValue();
        assertThat(captured.getSummaryCost()).isEqualTo(expectedSupplyDocument.getSummaryCost());
    }
}
