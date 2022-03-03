package rtuitlab.orders.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import rtuitlab.orders.models.OrderEntity;

public interface OrderRepository extends MongoRepository<OrderEntity, String> {
}
