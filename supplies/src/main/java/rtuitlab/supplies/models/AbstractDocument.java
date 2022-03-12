package rtuitlab.supplies.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public abstract class AbstractDocument implements Serializable {
    @Id
    protected String id;
}