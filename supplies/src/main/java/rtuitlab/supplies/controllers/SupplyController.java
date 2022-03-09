package rtuitlab.supplies.controllers;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rtuitlab.supplies.models.documents.SupplyDocument;
import rtuitlab.supplies.services.SupplyService;

@RestController
@RequestMapping("/api/v1/supplies")
@Api("Controller for supplies")
public class SupplyController extends AbstractController<SupplyDocument, SupplyService> {
    public SupplyController(SupplyService service) {
        super(service);
    }
}
