package rtuitlab.products.repositories;

import rtuitlab.products.entities.CategoryEntity;
import rtuitlab.products.entities.ProductEntity;

import java.util.List;

public interface ProductRepository extends CommonRepository<ProductEntity> {
    List<ProductEntity> findAllByCategoryEntity(CategoryEntity categoryEntity);
    ProductEntity findByName(String name);
}
