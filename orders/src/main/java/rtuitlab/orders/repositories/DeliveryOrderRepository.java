package rtuitlab.orders.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import rtuitlab.orders.models.documents.DeliveryOrderDocument;

public interface DeliveryOrderRepository extends CommonRepository<DeliveryOrderDocument> {
}
