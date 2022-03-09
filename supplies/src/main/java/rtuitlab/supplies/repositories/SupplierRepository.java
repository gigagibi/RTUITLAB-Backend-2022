package rtuitlab.supplies.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import rtuitlab.supplies.models.documents.SupplierDocument;

@Repository
public interface SupplierRepository extends CommonRepository<SupplierDocument> {
}
