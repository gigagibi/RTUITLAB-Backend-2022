package rtuitlab.products.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rtuitlab.products.dto.category.GetCategoryDTO;
import rtuitlab.products.dto.category.SetCategoryDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostPutProductDTO {
    private String name;
    private Integer cost;
    private String description;
    private String imagePath;
    private SetCategoryDTO category;
}
