package rtuitlab.supplies.controllers;

import lombok.AllArgsConstructor;
import rtuitlab.supplies.models.AbstractDocument;
import rtuitlab.supplies.services.CommonService;

import java.util.List;

@AllArgsConstructor
public abstract class AbstractController<E extends AbstractDocument, S extends CommonService<E>> implements CommonController<E> {
    private final S service;

    @Override
    public List<E> getAll() {
        return service.getAll();
    }

    @Override
    public E getById(String id) {
        return service.getById(id);
    }

    @Override
    public List<E> create(E e) {
        return service.create(e);
    }

    @Override
    public E update(String id, E e) {
        return service.update(id, e);
    }

    @Override
    public List<E> deleteById(String id) {
        return service.deleteById(id);
    }

    @Override
    public List<E> deleteAll() {
        return service.deleteAll();
    }
}
