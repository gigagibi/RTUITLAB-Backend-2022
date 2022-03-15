package rtuitlab.deliveries.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductReceiveDTO {
    private String name;
    private Integer cost;
    private String description;
    private CategoryReceiveDTO category;
}
