package rtuitlab.orders.dto.rabbit;

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
public class DeliveryOrderFromDeliveriesDTO implements Serializable {
    private Integer number;
    private List<BoughtProductInfo> products;
    private String address;
    private String phone;
}
