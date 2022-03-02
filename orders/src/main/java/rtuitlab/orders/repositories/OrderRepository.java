package rtuitlab.orders.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import rtuitlab.orders.models.Order;

public interface OrderRepository extends MongoRepository<Order, Integer> {
}
