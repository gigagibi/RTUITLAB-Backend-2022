package rtuitlab.orders.dto.deliveryOrder.rabbit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rtuitlab.orders.models.BoughtProductInfo;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryOrderFromDeliveriesRabbitDTO implements Serializable {
    private List<BoughtProductInfo> products;
    private String address;
    private String phone;
}
