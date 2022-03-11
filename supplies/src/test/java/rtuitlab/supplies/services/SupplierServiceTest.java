package rtuitlab.supplies.services;

import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import rtuitlab.supplies.dto.supplier.SupplierGetDTO;
import rtuitlab.supplies.dto.supplier.SupplierPostPutDTO;
import rtuitlab.supplies.mappers.SupplierMapper;
import rtuitlab.supplies.models.SupplyProductInfo;
import rtuitlab.supplies.models.documents.SupplierDocument;
import rtuitlab.supplies.models.documents.SupplyDocument;
import rtuitlab.supplies.repositories.SupplierRepository;
import rtuitlab.supplies.repositories.SupplyRepository;

import java.util.Date;
import java.util.List;

public class SupplierServiceTest extends AbstractServiceTest<SupplierDocument, SupplierGetDTO, SupplierPostPutDTO, SupplierService, SupplierMapper, SupplierRepository> {
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
        this.gSupplier = () -> new SupplierGetDTO(
                "1",
                "supplier",
                "address",
                "phone"
        );
        this.pSupplier = () -> new SupplierPostPutDTO(
                "supplier",
                "address",
                "phone"
        );
        this.eArgumentCaptorSupplier = () -> ArgumentCaptor.forClass(SupplierDocument.class);
    }
}
