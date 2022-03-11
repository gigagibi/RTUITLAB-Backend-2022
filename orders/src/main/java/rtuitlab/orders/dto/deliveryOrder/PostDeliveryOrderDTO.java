package rtuitlab.orders.dto.deliveryOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rtuitlab.orders.models.BoughtProductInfo;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDeliveryOrderDTO {
    private Integer number;
    private List<BoughtProductInfo> products;
    private String address;
    private String phone;
}
