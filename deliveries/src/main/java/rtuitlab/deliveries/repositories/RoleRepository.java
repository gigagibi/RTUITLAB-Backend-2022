package rtuitlab.deliveries.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rtuitlab.deliveries.entities.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findByName(String name);
}
