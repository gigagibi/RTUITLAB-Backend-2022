package rtuitlab.orders.dto.deliveryOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PutDeliveryOrderDTO {
    private Integer number;
    private Integer cost;
    private List<Integer> productsIds;
    private OffsetDateTime orderDate;
    private String address;
    private String phone;
}