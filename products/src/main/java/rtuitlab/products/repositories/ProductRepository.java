package rtuitlab.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rtuitlab.products.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
