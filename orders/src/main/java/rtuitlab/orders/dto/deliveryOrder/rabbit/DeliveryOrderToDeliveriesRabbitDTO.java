package rtuitlab.orders.dto.deliveryOrder.rabbit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rtuitlab.orders.models.BoughtProductInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryOrderToDeliveriesRabbitDTO implements Serializable {
    private Integer number;
    private Integer cost;
    private List<BoughtProductInfo> products;
    private Date orderDate;
    private String address;
    private String phone;
}
