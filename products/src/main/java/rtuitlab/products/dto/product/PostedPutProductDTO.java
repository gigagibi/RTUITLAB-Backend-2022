package rtuitlab.products.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rtuitlab.products.dto.category.GetCategoryDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostedPutProductDTO {
    private Integer id;
    private String name;
    private Integer cost;
    private String description;
    private String imagePath;
    private GetCategoryDTO category;
}
