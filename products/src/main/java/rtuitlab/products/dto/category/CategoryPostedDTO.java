package rtuitlab.products.dto.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rtuitlab.products.dto.AbstractPostedDTO;

@Getter
@Setter
@NoArgsConstructor
public class CategoryPostedDTO extends AbstractPostedDTO {
    private String name;

    public CategoryPostedDTO(Integer id, String name) {
        super(id);
        this.name = name;
    }
}
