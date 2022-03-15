package rtuitlab.products.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rtuitlab.products.dto.AbstractPostedDTO;
import rtuitlab.products.dto.category.CategoryGetDTO;

@Getter
@Setter
@NoArgsConstructor
public class ProductPostedDTO extends AbstractPostedDTO {
    private String name;
    private Integer cost;
    private String description;
    private String imagePath;
    private CategoryGetDTO category;

    public ProductPostedDTO(Integer id, String name, Integer cost, String description, String imagePath, CategoryGetDTO category) {
        super(id);
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.imagePath = imagePath;
        this.category = category;
    }
}
