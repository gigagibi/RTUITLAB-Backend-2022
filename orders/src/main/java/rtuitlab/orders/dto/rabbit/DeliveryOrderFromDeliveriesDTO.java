package rtuitlab.orders.dto.rabbit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rtuitlab.orders.models.BoughtProductInfo;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryOrderFromDeliveriesDTO {
    private Integer number;
    private List<BoughtProductInfo> products;
    private String address;
    private String phone;
}
