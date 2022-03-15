package rtuitlab.deliveries.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderReceiveDTO {
    private Integer number;
    private Integer cost;
    private List<BoughProductInfo> products;
    private Date orderDate;
    private String address;
    private String phone;
}
