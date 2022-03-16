package rtuitlab.products.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class ProductEntity extends AbstractEntity{
    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private Integer cost;

    @Column(name = "description")
    private String description;

    @Column(name = "image_path")
    private String imagePath;

    @ManyToOne
    private CategoryEntity categoryEntity;

    public ProductEntity(Integer id, String name, Integer cost, String description, String imagePath, CategoryEntity categoryEntity) {
        super(id);
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.imagePath = imagePath;
        this.categoryEntity = categoryEntity;
    }

    public ProductEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }
}
