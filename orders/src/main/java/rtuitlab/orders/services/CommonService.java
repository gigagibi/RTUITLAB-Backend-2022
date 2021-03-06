package rtuitlab.orders.services;

import rtuitlab.orders.exceptions.EntityNotFoundException;
import rtuitlab.orders.models.documents.AbstractDocument;
import rtuitlab.orders.dto.*;

import java.util.List;

public interface CommonService<E extends AbstractDocument, Get extends AbstractGetDTO, Post extends AbstractPostDTO, Put extends AbstractPutDTO, Posted extends AbstractPostedDTO, Updated extends AbstractUpdatedDTO> {
    List<Get> getAll();

    Get getById(String id) throws EntityNotFoundException;

    List<Posted> create(Post p);

    Updated update(String id, Put p) throws EntityNotFoundException;

    List<Get> deleteById(String id) throws EntityNotFoundException;

    List<Get> deleteAll();
}
