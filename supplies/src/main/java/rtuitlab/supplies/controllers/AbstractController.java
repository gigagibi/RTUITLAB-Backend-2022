package rtuitlab.supplies.controllers;

import lombok.AllArgsConstructor;
import rtuitlab.supplies.dto.AbstractGetDTO;
import rtuitlab.supplies.dto.AbstractPostPutDTO;
import rtuitlab.supplies.models.AbstractDocument;
import rtuitlab.supplies.services.CommonService;

import java.util.List;

@AllArgsConstructor
public abstract class AbstractController<E extends AbstractDocument, G extends AbstractGetDTO, P extends AbstractPostPutDTO, S extends CommonService<E, G, P>> implements CommonController<E, G, P> {
    protected final S service;

    @Override
    public List<G> getAll() {
        return service.getAll();
    }

    @Override
    public G getById(String id) {
        return service.getById(id);
    }

    @Override
    public List<G> create(P p) {
        return service.create(p);
    }

    @Override
    public G update(String id, P p) {
        return service.update(id, p);
    }

    @Override
    public List<G> deleteById(String id) {
        return service.deleteById(id);
    }

    @Override
    public List<G> deleteAll() {
        return null;
    }
}
