package rtuitlab.products.dto.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rtuitlab.products.dto.AbstractPutDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryPutDTO extends AbstractPutDTO {
    private String name;
}
