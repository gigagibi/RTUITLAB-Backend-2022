package rtuitlab.supplies.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;
import rtuitlab.supplies.models.documents.AbstractDocument;

@NoRepositoryBean
public interface CommonRepository<E extends AbstractDocument> extends MongoRepository<E, String> {

}
