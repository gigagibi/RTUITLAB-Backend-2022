package rtuitlab.supplies.mappers;

import rtuitlab.supplies.dto.supplier.SupplierGetDTO;
import rtuitlab.supplies.dto.supplier.SupplierPostPutDTO;
import rtuitlab.supplies.models.documents.SupplierDocument;

import static org.junit.jupiter.api.Assertions.*;

public class SupplierMapperTest extends AbstractMapperTest<SupplierDocument, SupplierGetDTO, SupplierPostPutDTO, SupplierMapper> {
    public SupplierMapperTest() {
        this.mapper = new SupplierMapperImpl();
        this.eSupplier = () -> new SupplierDocument(
                "1",
                "supplier",
                "address",
                "phone");
        this.gSupplier = () -> new SupplierGetDTO(
                "1",
                "supplier",
                "address",
                "phone"
        );
        this.pSupplier = () -> new SupplierPostPutDTO(
                "supplier",
                "address",
                "phone"
        );
    }
}