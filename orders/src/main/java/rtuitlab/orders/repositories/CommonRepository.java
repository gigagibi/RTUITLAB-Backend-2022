package rtuitlab.orders.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import rtuitlab.orders.models.documents.AbstractDocument;

public interface CommonRepository<E extends AbstractDocument> extends MongoRepository<E, String> {
}
