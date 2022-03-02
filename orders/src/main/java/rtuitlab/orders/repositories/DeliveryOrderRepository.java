package rtuitlab.orders.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import rtuitlab.orders.models.DeliveryOrder;

public interface DeliveryOrderRepository extends MongoRepository<DeliveryOrder, Integer> {
}
