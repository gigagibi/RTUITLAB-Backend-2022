package rtuitlab.orders.dto.deliveryOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rtuitlab.orders.dto.AbstractPostedDTO;
import rtuitlab.orders.models.BoughtProductInfo;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DeliveryOrderPostedDTO extends AbstractPostedDTO {
    private Integer number;
    private Integer cost;
    private List<BoughtProductInfo> products;
    private Date orderDate;
    private String address;
    private String phone;

    public DeliveryOrderPostedDTO(String id, Integer number, Integer cost, List<BoughtProductInfo> products, Date orderDate, String address, String phone) {
        super(id);
        this.number = number;
        this.cost = cost;
        this.products = products;
        this.orderDate = orderDate;
        this.address = address;
        this.phone = phone;
    }
}
