package rtuitlab.products.dto.rabbit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductToDeliveriesDTO implements Serializable {
    private Integer id;
    private String name;
    private Integer cost;
    private String description;
    private CategoryToDeliveriesDTO category;
}
