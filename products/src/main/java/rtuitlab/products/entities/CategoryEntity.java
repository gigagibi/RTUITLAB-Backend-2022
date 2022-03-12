package rtuitlab.products.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
public class CategoryEntity extends AbstractEntity {
    @Column(name = "name")
    private String name;

    public CategoryEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }
}
