package rtuitlab.supplies.unit.mappers;

import rtuitlab.supplies.dto.supply.*;
import rtuitlab.supplies.mappers.SupplyMapper;
import rtuitlab.supplies.mappers.SupplyMapperImpl;
import rtuitlab.supplies.models.SupplyProductInfo;
import rtuitlab.supplies.models.documents.SupplyDocument;

import java.util.Date;
import java.util.List;

public class SupplyMapperTest extends AbstractMapperTest<SupplyDocument, SupplyGetDTO, SupplyPostDTO, SupplyPutDTO, SupplyPostedDTO, SupplyUpdatedDTO, SupplyMapper> {
    public SupplyMapperTest() {
        this.mapper = new SupplyMapperImpl();
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
    }
}