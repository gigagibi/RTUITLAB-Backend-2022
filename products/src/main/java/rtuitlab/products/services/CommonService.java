package rtuitlab.products.services;

import rtuitlab.products.dto.*;
import rtuitlab.products.entities.AbstractEntity;
import rtuitlab.products.exception.EntityNotFoundException;

import java.util.List;

public interface CommonService<E extends AbstractEntity, Get extends AbstractGetDTO, Post extends AbstractPostDTO, Put extends AbstractPutDTO, Posted extends AbstractPostedDTO, Updated extends AbstractUpdatedDTO> {
    List<Get> getAll();

    Get getById(int id) throws EntityNotFoundException;

    List<Posted> create(Post p);

    Updated update(int id, Put p) throws EntityNotFoundException;

    List<Get> deleteById(int id) throws EntityNotFoundException;

    List<Get> deleteAll();
}

