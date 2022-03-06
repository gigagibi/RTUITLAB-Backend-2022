package rtuitlab.supplies.services;

import org.springframework.stereotype.Service;
import rtuitlab.supplies.models.documents.SupplyDocument;
import rtuitlab.supplies.repositories.SupplyRepository;

import java.util.Date;
import java.util.List;

@Service
public class SupplyService extends AbstractService<SupplyDocument, SupplyRepository> {
    public SupplyService(SupplyRepository repository) {
        super(repository);
    }

    @Override
    public List<SupplyDocument> create(SupplyDocument supplyDocument) {
        supplyDocument.setSupplyDate(new Date());
        repository.save(supplyDocument);
        return repository.findAll();
    }
}
