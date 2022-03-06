package rtuitlab.supplies.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rtuitlab.supplies.models.documents.SupplierDocument;
import rtuitlab.supplies.services.SupplierService;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController extends AbstractController<SupplierDocument, SupplierService> {
    public SupplierController(SupplierService service) {
        super(service);
    }
}
