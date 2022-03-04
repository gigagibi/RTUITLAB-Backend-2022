package rtuitlab.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rtuitlab.products.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
}
