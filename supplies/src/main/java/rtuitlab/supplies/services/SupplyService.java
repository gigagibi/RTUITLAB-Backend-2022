package rtuitlab.supplies.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import rtuitlab.supplies.dto.supply.*;
import rtuitlab.supplies.exceptions.EntityNotFoundException;
import rtuitlab.supplies.mappers.SupplyMapper;
import rtuitlab.supplies.models.SupplyProductInfo;
import rtuitlab.supplies.models.documents.SupplyDocument;

import rtuitlab.supplies.repositories.SupplyRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class SupplyService extends AbstractService<SupplyDocument, SupplyRepository, SupplyGetDTO, SupplyPostDTO, SupplyPutDTO, SupplyPostedDTO, SupplyUpdatedDTO, SupplyMapper> {
    public SupplyService(SupplyRepository repository, @Qualifier("supplyMapperImpl") SupplyMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public List<SupplyPostedDTO> create(SupplyPostDTO supplyPostDTO) {
        SupplyDocument supplyDocument = mapper.postDTOToEntity(supplyPostDTO);
        int summaryCost=0;
        for (SupplyProductInfo supplyProductInfo : supplyDocument.getSupplyProductInfos()){
            summaryCost += supplyProductInfo.getCost() * supplyProductInfo.getAmount();
        }
        supplyDocument.setSummaryCost(summaryCost);
        repository.save(supplyDocument);
        return repository.findAll().stream().map(mapper::entityToPostedDTO).collect(Collectors.toList());
    }

    @Override
    public SupplyUpdatedDTO update(String id, SupplyPutDTO supplyPutDTO) throws EntityNotFoundException {
        if(!repository.existsById(id))
            throw new EntityNotFoundException(id);
        SupplyDocument supplyDocument = mapper.putDTOToEntity(supplyPutDTO);
        supplyDocument.setId(id);
        int summaryCost=0;
        for (SupplyProductInfo supplyProductInfo : supplyDocument.getSupplyProductInfos()){
            summaryCost += supplyProductInfo.getCost() * supplyProductInfo.getAmount();
        }
        supplyDocument.setSummaryCost(summaryCost);
        repository.save(supplyDocument);
        return mapper.entityToUpdatedDTO(supplyDocument);
    }
}
