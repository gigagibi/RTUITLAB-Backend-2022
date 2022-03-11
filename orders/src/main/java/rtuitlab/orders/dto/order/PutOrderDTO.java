package rtuitlab.orders.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rtuitlab.orders.models.BoughtProductInfo;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PutOrderDTO {
    private Integer number;
    private List<BoughtProductInfo> products;
    private Date orderDate;
}
