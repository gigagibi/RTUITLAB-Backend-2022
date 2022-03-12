package rtuitlab.supplies.mappers;

import rtuitlab.supplies.dto.*;
import rtuitlab.supplies.models.AbstractDocument;

public interface CommonMapper<E extends AbstractDocument, Get extends AbstractGetDTO, Post extends AbstractPostDTO, Put extends AbstractPutDTO, Posted extends AbstractPostedDTO, Updated extends AbstractUpdatedDTO> {
    E postDTOToEntity(Post post);
    E putDTOToEntity(Put put);
    Get entityToGetDTO(E e);
    Posted entityToPostedDTO(E e);
    Updated entityToUpdatedDTO(E e);
}
