package rtuitlab.supplies.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import rtuitlab.supplies.dto.supply.SupplyGetDTO;
import rtuitlab.supplies.dto.supply.SupplyPostPutDTO;
import rtuitlab.supplies.models.documents.SupplyDocument;

@Component
@Mapper(componentModel = "spring")
public interface SupplyMapper extends CommonMapper<SupplyDocument, SupplyGetDTO, SupplyPostPutDTO> {
}
