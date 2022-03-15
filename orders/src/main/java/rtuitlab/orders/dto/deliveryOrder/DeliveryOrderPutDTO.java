package rtuitlab.orders.dto.deliveryOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rtuitlab.orders.dto.AbstractPutDTO;
import rtuitlab.orders.models.BoughtProductInfo;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryOrderPutDTO extends AbstractPutDTO {
    private Integer number;
    private List<BoughtProductInfo> products;
    private Date orderDate;
    private String address;
    private String phone;
}
