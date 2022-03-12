package rtuitlab.supplies.dto.supplier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rtuitlab.supplies.dto.AbstractPutDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierPutDTO extends AbstractPutDTO {
    private String name;
    private String address;
    private String phone;
}
