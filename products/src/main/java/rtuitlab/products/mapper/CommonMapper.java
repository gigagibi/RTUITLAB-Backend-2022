package rtuitlab.products.mapper;

import rtuitlab.products.dto.*;
import rtuitlab.products.entities.AbstractEntity;

public interface CommonMapper<E extends AbstractEntity, Get extends AbstractGetDTO, Post extends AbstractPostDTO, Put extends AbstractPutDTO, Posted extends AbstractPostedDTO, Updated extends AbstractUpdatedDTO> {
    E postDTOToEntity(Post post);
    E putDTOToEntity(Put put);
    Get entityToGetDTO(E e);
    Posted entityToPostedDTO(E e);
    Updated entityToUpdatedDTO(E e);
}
