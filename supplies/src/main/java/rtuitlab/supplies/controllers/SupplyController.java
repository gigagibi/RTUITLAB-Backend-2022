package rtuitlab.supplies.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rtuitlab.supplies.models.documents.SupplyDocument;
import rtuitlab.supplies.services.SupplyService;

@RestController
@RequestMapping("/api/supplies")
public class SupplyController extends AbstractController<SupplyDocument, SupplyService> {
    public SupplyController(SupplyService service) {
        super(service);
    }
}
