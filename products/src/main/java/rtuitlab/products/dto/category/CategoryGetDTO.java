package rtuitlab.products.dto.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rtuitlab.products.dto.AbstractGetDTO;

@Getter
@Setter
@NoArgsConstructor
public class CategoryGetDTO extends AbstractGetDTO {
    private String name;

    public CategoryGetDTO(Integer id, String name) {
        super(id);
        this.name = name;
    }
}
