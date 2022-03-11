package rtuitlab.supplies.mappers;

import org.mapstruct.Mapper;
import rtuitlab.supplies.dto.AbstractGetDTO;
import rtuitlab.supplies.dto.AbstractPostPutDTO;
import rtuitlab.supplies.models.AbstractDocument;

public interface CommonMapper<E extends AbstractDocument, G extends AbstractGetDTO, P extends AbstractPostPutDTO> {
    E postPutDTOToEntity(P postPutDTO);
    G entityToDTO(E entity);
}
