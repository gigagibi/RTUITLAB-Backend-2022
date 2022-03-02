package rtuitlab.orders.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;
import java.util.List;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryOrder {
    @Id
    private Integer id;
    private Integer number;
    private Integer cost;
    private List<Integer> productsIds;
    private OffsetDateTime orderDate;
    private String address;
    private String phone;
}
