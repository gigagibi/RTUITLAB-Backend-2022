package rtuitlab.products.dto.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rtuitlab.products.dto.AbstractUpdatedDTO;

@Getter
@Setter
@NoArgsConstructor
public class CategoryUpdatedDTO extends AbstractUpdatedDTO {
    private String name;

    public CategoryUpdatedDTO(Integer id, String name) {
        super(id);
        this.name = name;
    }
}
