package rtuitlab.supplies.integration.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import rtuitlab.supplies.controllers.SupplierController;
import rtuitlab.supplies.dto.supplier.*;
import rtuitlab.supplies.models.documents.SupplierDocument;
import rtuitlab.supplies.services.SupplierService;

@WebMvcTest(SupplierController.class)
public class SupplierControllerTest extends AbstractControllerTest<SupplierDocument, SupplierGetDTO, SupplierPostDTO, SupplierPutDTO, SupplierPostedDTO, SupplierUpdatedDTO, SupplierService, SupplierController> {
    public SupplierControllerTest() {
        this.apiUrl = "/api/v1/suppliers";
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
        this.objectMapper = new ObjectMapper();
    }
}
