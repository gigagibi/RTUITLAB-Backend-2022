package rtuitlab.supplies.services;

import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import rtuitlab.supplies.dto.supplier.SupplierGetDTO;
import rtuitlab.supplies.dto.supply.SupplyGetDTO;
import rtuitlab.supplies.dto.supply.SupplyPostPutDTO;
import rtuitlab.supplies.mappers.SupplyMapper;
import rtuitlab.supplies.models.SupplyProductInfo;
import rtuitlab.supplies.models.documents.SupplyDocument;
import rtuitlab.supplies.repositories.SupplyRepository;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class SupplyServiceTest extends AbstractServiceTest<SupplyDocument, SupplyGetDTO, SupplyPostPutDTO, SupplyService, SupplyMapper, SupplyRepository> {
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
                List.of(new SupplyProductInfo("product", "desc", 1, 100)),
                100);
        this.gSupplier = () -> new SupplyGetDTO(
                "1",
                "1",
                new Date(1),
                List.of(new SupplyProductInfo("product", "desc", 1, 100)),
                100
        );
        this.pSupplier = () -> new SupplyPostPutDTO(
                "1",
                new Date(1),
                List.of(new SupplyProductInfo("product", "desc", 1, 100)),
                100
        );
        this.eArgumentCaptorSupplier = () -> ArgumentCaptor.forClass(SupplyDocument.class);
    }
}
