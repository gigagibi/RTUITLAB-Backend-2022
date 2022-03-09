package rtuitlab.supplies.services;

import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import rtuitlab.supplies.models.SupplyProductInfo;
import rtuitlab.supplies.models.documents.SupplyDocument;
import rtuitlab.supplies.repositories.SupplyRepository;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class SupplyServiceTest extends AbstractServiceTest<SupplyDocument, SupplyService, SupplyRepository> {
    public SupplyServiceTest() {
        SupplyRepository supplyMockRepository = Mockito.mock(SupplyRepository.class);
        this.mockRepository = supplyMockRepository;
        this.service = new SupplyService(supplyMockRepository);
        this.eSupplier = () -> new SupplyDocument(
                "1",
                "1",
                new Date(),
                List.of(new SupplyProductInfo("product", "desc", 1, 100)),
                100);
        this.eArgumentCaptorSupplier = () -> ArgumentCaptor.forClass(SupplyDocument.class);
    }
}
