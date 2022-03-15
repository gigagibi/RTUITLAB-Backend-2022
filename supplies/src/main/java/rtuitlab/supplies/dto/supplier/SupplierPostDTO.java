package rtuitlab.supplies.dto.supplier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rtuitlab.supplies.dto.AbstractPostDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierPostDTO extends AbstractPostDTO {
    private String name;
    private String address;
    private String phone;
}
