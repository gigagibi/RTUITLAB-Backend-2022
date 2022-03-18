package rtuitlab.supplies.integration.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import rtuitlab.supplies.controllers.SupplyController;
import rtuitlab.supplies.dto.supply.*;
import rtuitlab.supplies.models.SupplyProductInfo;
import rtuitlab.supplies.models.documents.SupplyDocument;
import rtuitlab.supplies.services.SupplyService;
import rtuitlab.supplies.integration.controllers.AbstractControllerTest;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@WebMvcTest(SupplyController.class)
public class SupplyControllerTest extends AbstractControllerTest<SupplyDocument, SupplyGetDTO, SupplyPostDTO, SupplyPutDTO, SupplyPostedDTO, SupplyUpdatedDTO, SupplyService, rtuitlab.supplies.controllers.SupplyController> {
    public SupplyControllerTest() {
        this.apiUrl = "/api/v1/supplies";
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
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+00:00");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        objectMapper.setDateFormat(simpleDateFormat);
        this.objectMapper = objectMapper;
    }
}
