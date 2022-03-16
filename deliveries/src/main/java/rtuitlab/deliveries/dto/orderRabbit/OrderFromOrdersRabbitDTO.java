package rtuitlab.deliveries.dto.orderRabbit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderFromOrdersRabbitDTO implements Serializable {
    private Integer number;
    private Integer cost;
    private List<BoughtProductInfo> products;
    private Date orderDate;
    private String address;
    private String phone;
}
