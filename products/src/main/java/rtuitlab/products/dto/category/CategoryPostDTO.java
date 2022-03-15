package rtuitlab.products.dto.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rtuitlab.products.dto.AbstractPostDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryPostDTO extends AbstractPostDTO {
    private String name;
}
