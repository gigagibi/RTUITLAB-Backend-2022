package rtuitlab.supplies.services;

import rtuitlab.supplies.dto.*;
import rtuitlab.supplies.exceptions.EntityNotFoundException;
import rtuitlab.supplies.models.documents.AbstractDocument;

import java.util.List;

public interface CommonService<E extends AbstractDocument, Get extends AbstractGetDTO, Post extends AbstractPostDTO, Put extends AbstractPutDTO, Posted extends AbstractPostedDTO, Updated extends AbstractUpdatedDTO> {
    List<Get> getAll();

    Get getById(String id) throws EntityNotFoundException;

    List<Posted> create(Post p);

    Updated update(String id, Put p) throws EntityNotFoundException;

    List<Get> deleteById(String id) throws EntityNotFoundException;

    List<Get> deleteAll();
}
