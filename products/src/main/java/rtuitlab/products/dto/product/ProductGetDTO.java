package rtuitlab.products.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rtuitlab.products.dto.AbstractGetDTO;
import rtuitlab.products.dto.category.CategoryGetDTO;

@Getter
@Setter
@NoArgsConstructor
public class ProductGetDTO extends AbstractGetDTO {
    private String name;
    private Integer cost;
    private String description;
    private CategoryGetDTO category;

    public ProductGetDTO(Integer id, String name, Integer cost, String description, CategoryGetDTO category) {
        super(id);
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.category = category;
    }
}
