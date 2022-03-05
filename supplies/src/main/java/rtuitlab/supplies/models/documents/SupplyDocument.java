package rtuitlab.supplies.models.documents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import rtuitlab.supplies.models.ProductInfo;

import java.util.Date;
import java.util.List;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplyDocument {
    @Id
    private String id;
    private String supplier_id;
    private Date supplyDate;
    List<ProductInfo> productInfos;
    private Integer summaryCost;
}
