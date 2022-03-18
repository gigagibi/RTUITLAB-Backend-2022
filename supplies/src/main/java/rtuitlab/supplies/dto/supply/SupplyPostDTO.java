package rtuitlab.supplies.dto.supply;

import lombok.*;
import rtuitlab.supplies.dto.AbstractPostDTO;
import rtuitlab.supplies.models.SupplyProductInfo;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplyPostDTO extends AbstractPostDTO {
    private String supplier_id;
    private Date supplyDate;
    List<SupplyProductInfo> supplyProductInfos;
}
