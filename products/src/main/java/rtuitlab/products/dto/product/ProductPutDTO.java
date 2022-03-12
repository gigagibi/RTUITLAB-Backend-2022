package rtuitlab.products.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rtuitlab.products.dto.AbstractPutDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductPutDTO extends AbstractPutDTO {
    private String name;
    private Integer cost;
    private String description;
    private String imagePath;
    private Integer categoryId;
}
