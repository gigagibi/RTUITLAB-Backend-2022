package rtuitlab.orders.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import rtuitlab.orders.models.DeliveryOrderEntity;

public interface DeliveryOrderRepository extends MongoRepository<DeliveryOrderEntity, String> {
}
