package rtuitlab.orders.dto.product.http;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rtuitlab.orders.dto.category.http.CategoryFromProductsHttpDTO;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductFromProductsHttpDTO implements Serializable {
    private Integer id;
    private String name;
    private Integer cost;
    private String description;
    private CategoryFromProductsHttpDTO category;
}
