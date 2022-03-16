package rtuitlab.orders.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoughtProductInfo implements Serializable {
    private Integer id;
    private Integer cost;
    private Integer amount;
}
