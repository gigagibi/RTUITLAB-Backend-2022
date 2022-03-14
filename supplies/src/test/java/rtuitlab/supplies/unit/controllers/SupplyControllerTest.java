package rtuitlab.supplies.unit.controllers;

import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import rtuitlab.supplies.controllers.SupplyController;
import rtuitlab.supplies.dto.supply.*;
import rtuitlab.supplies.models.SupplyProductInfo;
import rtuitlab.supplies.models.documents.SupplyDocument;
import rtuitlab.supplies.services.SupplyService;

import java.util.Date;
import java.util.List;

public class SupplyControllerTest extends AbstractControllerTest<SupplyDocument, SupplyGetDTO, SupplyPostDTO, SupplyPutDTO, SupplyPostedDTO, SupplyUpdatedDTO, SupplyService, SupplyController> {
    public SupplyControllerTest() {
        SupplyService supplyService = Mockito.mock(SupplyService.class);
        this.mockService = supplyService;
        this.controller = new SupplyController(supplyService);
        this.eSupplier = () -> new SupplyDocument(
                "1",
                "1",
                new Date(1),
                List.of(new SupplyProductInfo("product", "desc", 1, 100)),
                100);
        this.getSupplier = () -> new SupplyGetDTO(
                "1",
                "1",
                new Date(1),
                List.of(new SupplyProductInfo("product", "desc", 1, 100), new SupplyProductInfo("product2", "desc2", 2, 200)),
                500
        );
        this.postSupplier = () -> new SupplyPostDTO(
                "1",
                new Date(1),
                List.of(new SupplyProductInfo("product", "desc", 1, 100), new SupplyProductInfo("product2", "desc2", 2, 200))
        );
        this.putSupplier = () -> new SupplyPutDTO(
                "1",
                new Date(1),
                List.of(new SupplyProductInfo("product", "desc", 1, 100), new SupplyProductInfo("product2", "desc2", 2, 200))
        );
        this.postedSupplier = () -> new SupplyPostedDTO(
                "1",
                "1",
                new Date(1),
                List.of(new SupplyProductInfo("product", "desc", 1, 100), new SupplyProductInfo("product2", "desc2", 2, 200))
        );
        this.updatedSupplier = () -> new SupplyUpdatedDTO(
                "1",
                "1",
                new Date(1),
                List.of(new SupplyProductInfo("product", "desc", 1, 100), new SupplyProductInfo("product2", "desc2", 2, 200))
        );
        this.postArgumentCaptorSupplier = () -> ArgumentCaptor.forClass(SupplyPostDTO.class);
        this.putArgumentCaptorSupplier = () -> ArgumentCaptor.forClass(SupplyPutDTO.class);
    }
}
