package rtuitlab.orders.dto.deliveryOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rtuitlab.orders.dto.AbstractPostDTO;
import rtuitlab.orders.models.BoughtProductInfo;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryOrderPostDTO extends AbstractPostDTO {
    private Integer number;
    private List<BoughtProductInfo> products;
    private String address;
    private String phone;
}
