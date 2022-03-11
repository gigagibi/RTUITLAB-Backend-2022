package rtuitlab.supplies.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import rtuitlab.supplies.dto.supply.SupplyGetDTO;
import rtuitlab.supplies.dto.supply.SupplyPostPutDTO;
import rtuitlab.supplies.mappers.SupplyMapper;
import rtuitlab.supplies.models.SupplyProductInfo;
import rtuitlab.supplies.models.documents.SupplyDocument;

import rtuitlab.supplies.repositories.SupplyRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class SupplyService extends AbstractService<SupplyDocument, SupplyRepository, SupplyGetDTO, SupplyPostPutDTO, SupplyMapper>{
    public SupplyService(SupplyRepository repository, @Qualifier("supplyMapperImpl") SupplyMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public List<SupplyGetDTO> create(SupplyPostPutDTO supplyPostPutDTO) {
        SupplyDocument supplyDocument = mapper.postPutDTOToEntity(supplyPostPutDTO);
        int summaryCost=0;
        for (SupplyProductInfo supplyProductInfo : supplyDocument.getSupplyProductInfos()){
            summaryCost += supplyProductInfo.getCost() * supplyProductInfo.getAmount();
        }
        supplyDocument.setSummaryCost(summaryCost);
        repository.save(supplyDocument);
        return repository.findAll().stream().map(mapper::entityToDTO).collect(Collectors.toList());
    }
}
