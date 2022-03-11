package rtuitlab.supplies.controllers;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rtuitlab.supplies.dto.supplier.SupplierGetDTO;
import rtuitlab.supplies.dto.supplier.SupplierPostPutDTO;
import rtuitlab.supplies.models.documents.SupplierDocument;
import rtuitlab.supplies.services.SupplierService;

@RestController
@RequestMapping("/api/v1/suppliers")
@Api("Controller for suppliers")
public class SupplierController extends AbstractController<SupplierDocument, SupplierGetDTO, SupplierPostPutDTO, SupplierService> {
    public SupplierController(SupplierService service) {
        super(service);
    }
}
