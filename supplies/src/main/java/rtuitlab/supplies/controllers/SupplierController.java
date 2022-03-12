package rtuitlab.supplies.controllers;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rtuitlab.supplies.dto.supplier.*;
import rtuitlab.supplies.models.documents.SupplierDocument;
import rtuitlab.supplies.services.SupplierService;

@RestController
@RequestMapping("/api/v1/suppliers")
@Api("Controller for suppliers")
public class SupplierController extends AbstractController<SupplierDocument, SupplierGetDTO, SupplierPostDTO, SupplierPutDTO, SupplierPostedDTO, SupplierUpdatedDTO, SupplierService> {
    public SupplierController(SupplierService service) {
        super(service);
    }
}
