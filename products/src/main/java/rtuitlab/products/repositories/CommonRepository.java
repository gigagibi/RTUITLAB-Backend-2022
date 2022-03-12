package rtuitlab.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rtuitlab.products.entities.AbstractEntity;

public interface CommonRepository<E extends AbstractEntity> extends JpaRepository<E, Integer> {

}
