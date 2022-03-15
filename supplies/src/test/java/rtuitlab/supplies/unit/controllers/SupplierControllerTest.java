package rtuitlab.supplies.unit.controllers;

import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import rtuitlab.supplies.controllers.SupplierController;
import rtuitlab.supplies.dto.supplier.*;
import rtuitlab.supplies.dto.supplier.SupplierPostDTO;
import rtuitlab.supplies.dto.supplier.SupplierPutDTO;
import rtuitlab.supplies.models.documents.SupplierDocument;
import rtuitlab.supplies.services.SupplierService;

public class SupplierControllerTest extends AbstractControllerTest<SupplierDocument, SupplierGetDTO, SupplierPostDTO, SupplierPutDTO, SupplierPostedDTO, SupplierUpdatedDTO, SupplierService, SupplierController> {
    public SupplierControllerTest() {
        SupplierService supplierService = Mockito.mock(SupplierService.class);
        this.mockService = supplierService;
        this.controller = new SupplierController(supplierService);
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
        this.postArgumentCaptorSupplier = () -> ArgumentCaptor.forClass(SupplierPostDTO.class);
        this.putArgumentCaptorSupplier = () -> ArgumentCaptor.forClass(SupplierPutDTO.class);
    }
}
