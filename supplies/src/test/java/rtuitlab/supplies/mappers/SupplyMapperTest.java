package rtuitlab.supplies.mappers;

import rtuitlab.supplies.dto.supply.SupplyGetDTO;
import rtuitlab.supplies.dto.supply.SupplyPostPutDTO;
import rtuitlab.supplies.models.SupplyProductInfo;
import rtuitlab.supplies.models.documents.SupplyDocument;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SupplyMapperTest extends AbstractMapperTest<SupplyDocument, SupplyGetDTO, SupplyPostPutDTO, SupplyMapper> {
    public SupplyMapperTest() {
        this.mapper = new SupplyMapperImpl();
        this.eSupplier = () -> new SupplyDocument(
                "1",
                "1",
                new Date(1),
                List.of(new SupplyProductInfo("product", "desc", 1, 100)),
                100);
        this.gSupplier = () -> new SupplyGetDTO(
                "1",
                "1",
                new Date(1),
                List.of(new SupplyProductInfo("product", "desc", 1, 100)),
                100
        );
        this.pSupplier = () -> new SupplyPostPutDTO(
                "1",
                new Date(1),
                List.of(new SupplyProductInfo("product", "desc", 1, 100)),
                100
        );
    }
}