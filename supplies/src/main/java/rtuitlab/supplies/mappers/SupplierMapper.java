package rtuitlab.supplies.mappers;


import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import rtuitlab.supplies.dto.supplier.*;
import rtuitlab.supplies.models.documents.SupplierDocument;

@Component
@Mapper(componentModel = "spring")
public interface SupplierMapper extends CommonMapper<SupplierDocument, SupplierGetDTO, SupplierPostDTO, SupplierPutDTO, SupplierPostedDTO, SupplierUpdatedDTO> {
}
