package rtuitlab.supplies.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplyProductInfo {
    private String name;
    private String description;
    private Integer amount;
    private Integer cost;
}
