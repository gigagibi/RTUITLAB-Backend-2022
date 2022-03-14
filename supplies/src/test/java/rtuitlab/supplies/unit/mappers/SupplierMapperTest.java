package rtuitlab.supplies.unit.mappers;

import rtuitlab.supplies.dto.supplier.*;
import rtuitlab.supplies.mappers.SupplierMapper;
import rtuitlab.supplies.mappers.SupplierMapperImpl;
import rtuitlab.supplies.models.documents.SupplierDocument;

public class SupplierMapperTest extends AbstractMapperTest<SupplierDocument, SupplierGetDTO, SupplierPostDTO, SupplierPutDTO, SupplierPostedDTO, SupplierUpdatedDTO, SupplierMapper> {
    public SupplierMapperTest() {
        this.mapper = new SupplierMapperImpl();
        this.eSupplier = () -> new SupplierDocument(
                "1",
                "supplier",
                "address",
                "phone");
        this.getSupplier = () -> new SupplierGetDTO(
                "1",
                "supplier",
                "address",
                "phone"
        );
        this.postSupplier = () -> new SupplierPostDTO(
                "supplier",
                "address",
                "phone"
        );
        this.putSupplier = () -> new SupplierPutDTO(
                "supplier",
                "address",
                "phone"
        );
        this.postedSupplier = () -> new SupplierPostedDTO(
                "1",
                "supplier",
                "address",
                "phone"
        );
        this.updatedSupplier = () -> new SupplierUpdatedDTO(
                "1",
                "supplier",
                "address",
                "phone"
        );
    }
}