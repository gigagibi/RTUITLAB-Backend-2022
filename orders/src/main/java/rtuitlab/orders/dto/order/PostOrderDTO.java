package rtuitlab.orders.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostOrderDTO {
    private Integer number;
    private Integer cost;
    private List<Integer> productsIds;
}
