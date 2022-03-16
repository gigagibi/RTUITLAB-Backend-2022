package rtuitlab.orders.dto.rabbit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rtuitlab.orders.models.BoughtProductInfo;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryOrderToDeliveriesDTO {
    private Integer number;
    private Integer cost;
    private List<BoughtProductInfo> products;
    private Date orderDate;
    private String address;
    private String phone;
}
