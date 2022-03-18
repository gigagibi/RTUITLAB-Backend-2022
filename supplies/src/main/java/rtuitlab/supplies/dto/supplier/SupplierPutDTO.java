package rtuitlab.supplies.dto.supplier;

import lombok.*;
import rtuitlab.supplies.dto.AbstractPutDTO;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierPutDTO extends AbstractPutDTO {
    private String name;
    private String address;
    private String phone;
}
