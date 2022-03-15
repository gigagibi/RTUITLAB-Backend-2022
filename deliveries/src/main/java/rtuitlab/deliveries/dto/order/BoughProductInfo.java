package rtuitlab.deliveries.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoughProductInfo {
    private Integer id;
    private Integer cost;
    private Integer amount;
}
