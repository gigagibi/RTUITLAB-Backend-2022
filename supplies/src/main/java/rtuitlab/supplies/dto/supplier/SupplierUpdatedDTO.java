package rtuitlab.supplies.dto.supplier;

import lombok.*;
import rtuitlab.supplies.dto.AbstractUpdatedDTO;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
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
