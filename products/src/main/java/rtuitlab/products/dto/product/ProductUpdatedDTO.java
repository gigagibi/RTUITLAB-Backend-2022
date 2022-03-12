package rtuitlab.products.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rtuitlab.products.dto.AbstractUpdatedDTO;
import rtuitlab.products.dto.category.CategoryGetDTO;

@Getter
@Setter
@NoArgsConstructor
public class ProductUpdatedDTO extends AbstractUpdatedDTO {
    private String name;
    private Integer cost;
    private String description;
    private String imagePath;
    private CategoryGetDTO category;

    public ProductUpdatedDTO(Integer id, String name, Integer cost, String description, String imagePath, CategoryGetDTO category) {
        super(id);
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.imagePath = imagePath;
        this.category = category;
    }
}
