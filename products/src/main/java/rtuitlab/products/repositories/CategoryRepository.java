package rtuitlab.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rtuitlab.products.entities.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
}
