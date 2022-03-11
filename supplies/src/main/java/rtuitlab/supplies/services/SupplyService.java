package rtuitlab.supplies.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import rtuitlab.supplies.dto.supply.SupplyGetDTO;
import rtuitlab.supplies.dto.supply.SupplyPostPutDTO;
import rtuitlab.supplies.mappers.SupplyMapper;
import rtuitlab.supplies.models.documents.SupplyDocument;

import rtuitlab.supplies.repositories.SupplyRepository;


@Service
public class SupplyService extends AbstractService<SupplyDocument, SupplyRepository, SupplyGetDTO, SupplyPostPutDTO, SupplyMapper>{
    public SupplyService(SupplyRepository repository, @Qualifier("supplyMapperImpl") SupplyMapper mapper) {
        super(repository, mapper);
    }
}
