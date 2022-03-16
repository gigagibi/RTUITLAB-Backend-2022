package rtuitlab.orders.dto.category.http;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryFromProductsHttpDTO implements Serializable {
    private Integer id;
    private String name;
}
