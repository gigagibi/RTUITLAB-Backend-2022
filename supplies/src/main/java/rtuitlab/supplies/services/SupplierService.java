package rtuitlab.supplies.services;

import org.springframework.stereotype.Service;
import rtuitlab.supplies.models.documents.SupplierDocument;
import rtuitlab.supplies.repositories.SupplierRepository;

@Service
public class SupplierService extends AbstractService<SupplierDocument, SupplierRepository>{
    public SupplierService(SupplierRepository repository) {
        super(repository);
    }
}
