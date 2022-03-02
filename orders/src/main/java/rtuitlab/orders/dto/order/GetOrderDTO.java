package rtuitlab.orders.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderDTO {
    private Integer id;
    private Integer number;
    private Integer cost;
    private List<Integer> productsIds;
    private OffsetDateTime orderDate;
}
