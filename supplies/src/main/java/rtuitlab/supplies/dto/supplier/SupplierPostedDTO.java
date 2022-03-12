package rtuitlab.supplies.dto.supplier;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rtuitlab.supplies.dto.AbstractPostedDTO;

@Getter
@Setter
@NoArgsConstructor
public class SupplierPostedDTO extends AbstractPostedDTO {
    private String name;
    private String address;
    private String phone;

    public SupplierPostedDTO(String id, String name, String address, String phone) {
        super(id);
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
}
