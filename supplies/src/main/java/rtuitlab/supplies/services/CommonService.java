package rtuitlab.supplies.services;

import rtuitlab.supplies.dto.AbstractGetDTO;
import rtuitlab.supplies.dto.AbstractPostPutDTO;
import rtuitlab.supplies.models.AbstractDocument;

import java.util.List;

public interface CommonService<E extends AbstractDocument, G extends AbstractGetDTO, P extends AbstractPostPutDTO> {
    List<G> getAll();

    G getById(String id);

    List<G> create(P p);

    G update(String id, P p);

    List<G> deleteById(String id);

    List<G> deleteAll();
}
