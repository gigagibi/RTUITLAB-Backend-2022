package rtuitlab.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rtuitlab.products.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
