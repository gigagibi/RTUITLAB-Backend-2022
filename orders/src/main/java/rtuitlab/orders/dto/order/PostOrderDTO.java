package rtuitlab.orders.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rtuitlab.orders.models.BoughtProductInfo;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostOrderDTO {
    private Integer number;
    private List<BoughtProductInfo> products;
}
