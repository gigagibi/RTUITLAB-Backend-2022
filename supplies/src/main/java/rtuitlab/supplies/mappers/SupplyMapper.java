package rtuitlab.supplies.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import rtuitlab.supplies.dto.supply.*;
import rtuitlab.supplies.models.documents.SupplyDocument;

@Component
@Mapper(componentModel = "spring")
public interface SupplyMapper extends CommonMapper<SupplyDocument, SupplyGetDTO, SupplyPostDTO, SupplyPutDTO, SupplyPostedDTO, SupplyUpdatedDTO> {
}
