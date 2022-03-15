package rtuitlab.supplies.dto.supplier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rtuitlab.supplies.dto.AbstractUpdatedDTO;

@Getter
@Setter
@NoArgsConstructor
public class SupplierUpdatedDTO extends AbstractUpdatedDTO {
    private String name;
    private String address;
    private String phone;

    public SupplierUpdatedDTO(String id, String name, String address, String phone) {
        super(id);
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
}
