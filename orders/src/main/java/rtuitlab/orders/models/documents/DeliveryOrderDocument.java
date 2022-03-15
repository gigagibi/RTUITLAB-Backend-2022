package rtuitlab.orders.models.documents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import rtuitlab.orders.models.BoughtProductInfo;

import java.util.Date;
import java.util.List;

@Document
@Getter
@Setter
@NoArgsConstructor
public class DeliveryOrderDocument extends AbstractDocument {
    private Integer number;
    private Integer cost;
    private List<BoughtProductInfo> products;
    private Date orderDate;
    private String address;
    private String phone;

    public DeliveryOrderDocument(String id, Integer number, Integer cost, List<BoughtProductInfo> products, Date orderDate, String address, String phone) {
        super(id);
        this.number = number;
        this.cost = cost;
        this.products = products;
        this.orderDate = orderDate;
        this.address = address;
        this.phone = phone;
    }
}
