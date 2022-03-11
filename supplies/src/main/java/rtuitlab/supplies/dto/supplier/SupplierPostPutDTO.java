package rtuitlab.supplies.dto.supplier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rtuitlab.supplies.dto.AbstractPostPutDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierPostPutDTO extends AbstractPostPutDTO {
    private String name;
    private String address;
    private String phone;
}
