package rtuitlab.products.dto.productRabbit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rtuitlab.products.dto.categoryRabbit.CategoryToDeliveriesDTO;

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
