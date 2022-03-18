package rtuitlab.supplies.dto.supplier;

import lombok.*;
import rtuitlab.supplies.dto.AbstractPostDTO;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierPostDTO extends AbstractPostDTO {
    private String name;
    private String address;
    private String phone;
}
