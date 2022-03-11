package rtuitlab.supplies.controllers;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rtuitlab.supplies.dto.supplier.SupplierGetDTO;
import rtuitlab.supplies.dto.supplier.SupplierPostPutDTO;
import rtuitlab.supplies.dto.supply.SupplyGetDTO;
import rtuitlab.supplies.dto.supply.SupplyPostPutDTO;
import rtuitlab.supplies.models.documents.SupplyDocument;
import rtuitlab.supplies.services.SupplyService;

@RestController
@RequestMapping("/api/v1/supplies")
@Api("Controller for supplies")
public class SupplyController extends AbstractController<SupplyDocument, SupplyGetDTO, SupplyPostPutDTO, SupplyService> {
    public SupplyController(SupplyService service) {
        super(service);
    }
}
