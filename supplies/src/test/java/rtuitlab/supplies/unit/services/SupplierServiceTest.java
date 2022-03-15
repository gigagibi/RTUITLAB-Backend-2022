package rtuitlab.supplies.unit.services;

import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import rtuitlab.supplies.dto.supplier.*;
import rtuitlab.supplies.mappers.SupplierMapper;
import rtuitlab.supplies.models.documents.SupplierDocument;
import rtuitlab.supplies.repositories.SupplierRepository;
import rtuitlab.supplies.services.SupplierService;

public class SupplierServiceTest extends AbstractServiceTest<SupplierDocument, SupplierGetDTO, SupplierPostDTO, SupplierPutDTO, SupplierPostedDTO, SupplierUpdatedDTO, SupplierService, SupplierMapper, SupplierRepository> {
    public SupplierServiceTest() {
        SupplierRepository supplierMockRepository = Mockito.mock(SupplierRepository.class);
        SupplierMapper supplierMockMapper = Mockito.mock(SupplierMapper.class);
        this.mockRepository = supplierMockRepository;
        this.mockMapper = supplierMockMapper;
        this.service = new SupplierService(supplierMockRepository, supplierMockMapper);
        this.eSupplier = () -> new SupplierDocument(
                "1",
                "supplier",
                "address",
                "phone");
        this.getSupplier = () -> new SupplierGetDTO(
                "1",
                "supplier",
                "address",
                "phone"
        );
        this.postSupplier = () -> new SupplierPostDTO(
                "supplier",
                "address",
                "phone"
        );
        this.putSupplier = () -> new SupplierPutDTO(
                "supplier",
                "address",
                "phone"
        );
        this.postedSupplier = () -> new SupplierPostedDTO(
                "1",
                "supplier",
                "address",
                "phone"
        );
        this.updatedSupplier = () -> new SupplierUpdatedDTO(
                "1",
                "supplier",
                "address",
                "phone"
        );
        this.eArgumentCaptorSupplier = () -> ArgumentCaptor.forClass(SupplierDocument.class);
    }
}
