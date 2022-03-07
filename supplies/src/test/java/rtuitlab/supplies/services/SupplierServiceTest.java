package rtuitlab.supplies.services;

import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import rtuitlab.supplies.models.SupplyProductInfo;
import rtuitlab.supplies.models.documents.SupplierDocument;
import rtuitlab.supplies.models.documents.SupplyDocument;
import rtuitlab.supplies.repositories.SupplierRepository;
import rtuitlab.supplies.repositories.SupplyRepository;

import java.util.Date;
import java.util.List;

public class SupplierServiceTest extends AbstractServiceTest<SupplierDocument, SupplierService, SupplierRepository> {
    public SupplierServiceTest() {
        SupplierRepository supplierMockRepository = Mockito.mock(SupplierRepository.class);
        this.mockRepository = supplierMockRepository;
        this.service = new SupplierService(supplierMockRepository);
        this.eSupplier = () -> new SupplierDocument(
                "1",
                "supplier",
                "address",
                "phone");
        this.eArgumentCaptorSupplier = () -> ArgumentCaptor.forClass(SupplierDocument.class);
    }
}
