package rtuitlab.supplies.dto.supply;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rtuitlab.supplies.dto.AbstractGetDTO;
import rtuitlab.supplies.models.SupplyProductInfo;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SupplyGetDTO extends AbstractGetDTO {
    private String supplier_id;
    private Date supplyDate;
    List<SupplyProductInfo> supplyProductInfos;
    private Integer summaryCost;

    public SupplyGetDTO(String id, String supplier_id, Date supplyDate, List<SupplyProductInfo> supplyProductInfos, Integer summaryCost) {
        super(id);
        this.supplier_id = supplier_id;
        this.supplyDate = supplyDate;
        this.supplyProductInfos = supplyProductInfos;
        this.summaryCost = summaryCost;
    }
}
