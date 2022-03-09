package rtuitlab.supplies.models.documents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import rtuitlab.supplies.models.AbstractDocument;

@Document
@Getter
@Setter
public class SupplierDocument extends AbstractDocument {
    private String name;
    private String address;
    private String phone;

    public SupplierDocument(String id, String name, String address, String phone) {
        super(id);
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
}