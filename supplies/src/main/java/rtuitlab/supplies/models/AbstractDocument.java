package rtuitlab.supplies.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
public abstract class AbstractDocument {
    @Id
    protected String id;
}