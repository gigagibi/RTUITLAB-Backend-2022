package rtuitlab.supplies.models.documents;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import rtuitlab.supplies.models.SupplyProductInfo;

import java.util.Date;
import java.util.List;

@Document
@Getter
@Setter
public class SupplyDocument extends AbstractDocument {
    private String supplier_id;
    private Date supplyDate;
    List<SupplyProductInfo> supplyProductInfos;
    private Integer summaryCost;

    public SupplyDocument(String id, String supplier_id, Date supplyDate, List<SupplyProductInfo> supplyProductInfos, Integer summaryCost) {
        super(id);
        this.supplier_id = supplier_id;
        this.supplyDate = supplyDate;
        this.supplyProductInfos = supplyProductInfos;
        this.summaryCost = summaryCost;
    }
}
