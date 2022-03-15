package rtuitlab.orders.dto.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rtuitlab.orders.dto.AbstractGetDTO;
import rtuitlab.orders.models.BoughtProductInfo;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderGetDTO extends AbstractGetDTO {
    private Integer number;
    private Integer cost;
    private List<BoughtProductInfo> products;
    private Date orderDate;

    public OrderGetDTO(String id, Integer number, Integer cost, List<BoughtProductInfo> products, Date orderDate) {
        super(id);
        this.number = number;
        this.cost = cost;
        this.products = products;
        this.orderDate = orderDate;
    }
}
