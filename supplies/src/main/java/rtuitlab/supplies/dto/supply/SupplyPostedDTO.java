package rtuitlab.supplies.dto.supply;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rtuitlab.supplies.dto.AbstractPostedDTO;
import rtuitlab.supplies.models.SupplyProductInfo;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SupplyPostedDTO extends AbstractPostedDTO {
    private String supplier_id;
    private Date supplyDate;
    List<SupplyProductInfo> supplyProductInfos;

    public SupplyPostedDTO(String id, String supplier_id, Date supplyDate, List<SupplyProductInfo> supplyProductInfos) {
        super(id);
        this.supplier_id = supplier_id;
        this.supplyDate = supplyDate;
        this.supplyProductInfos = supplyProductInfos;
    }
}
