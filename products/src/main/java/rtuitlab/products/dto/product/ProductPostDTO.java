package rtuitlab.products.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rtuitlab.products.dto.AbstractPostDTO;
import rtuitlab.products.dto.category.CategoryGetDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductPostDTO extends AbstractPostDTO {
    private String name;
    private Integer cost;
    private String description;
    private String imagePath;
    private Integer categoryId;
}
