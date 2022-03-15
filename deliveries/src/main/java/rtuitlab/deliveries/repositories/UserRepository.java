package rtuitlab.deliveries.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rtuitlab.deliveries.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity getByUsername(String username);
}
