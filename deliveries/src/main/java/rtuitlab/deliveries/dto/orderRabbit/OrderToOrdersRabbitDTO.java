package rtuitlab.deliveries.dto.orderRabbit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderToOrdersRabbitDTO implements Serializable {
    private List<BoughtProductInfo> products;
    private String address;
    private String phone;
}
