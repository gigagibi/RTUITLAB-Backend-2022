package rtuitlab.supplies.dto.supplier;

import lombok.*;
import rtuitlab.supplies.dto.AbstractGetDTO;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierGetDTO extends AbstractGetDTO {
    private String name;
    private String address;
    private String phone;

    public SupplierGetDTO(String id, String name, String address, String phone) {
        super(id);
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
}
