package rtuitlab.supplies.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import rtuitlab.supplies.dto.supplier.*;
import rtuitlab.supplies.mappers.SupplierMapper;
import rtuitlab.supplies.models.documents.SupplierDocument;
import rtuitlab.supplies.repositories.SupplierRepository;

@Service
public class SupplierService extends AbstractService<SupplierDocument, SupplierRepository, SupplierGetDTO, SupplierPostDTO, SupplierPutDTO, SupplierPostedDTO, SupplierUpdatedDTO, SupplierMapper> {
    public SupplierService(SupplierRepository repository, @Qualifier("supplierMapperImpl") SupplierMapper mapper) {
        super(repository, mapper);
    }
}
