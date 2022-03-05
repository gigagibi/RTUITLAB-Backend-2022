package rtuitlab.orders.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import rtuitlab.orders.models.documents.OrderDocument;

public interface OrderRepository extends MongoRepository<OrderDocument, String> {
}
