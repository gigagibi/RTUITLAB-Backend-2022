package rtuitlab.supplies.dto.supply;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rtuitlab.supplies.dto.AbstractPutDTO;
import rtuitlab.supplies.models.SupplyProductInfo;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplyPutDTO extends AbstractPutDTO {
    private String supplier_id;
    private Date supplyDate;
    List<SupplyProductInfo> supplyProductInfos;
}
