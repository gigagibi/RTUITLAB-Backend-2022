package rtuitlab.supplies.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import rtuitlab.supplies.models.documents.SupplyDocument;

@Repository
public interface SupplyRepository extends CommonRepository<SupplyDocument> {
}
