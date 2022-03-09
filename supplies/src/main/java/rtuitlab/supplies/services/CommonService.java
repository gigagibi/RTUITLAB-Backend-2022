package rtuitlab.supplies.services;

import rtuitlab.supplies.models.AbstractDocument;

import java.util.List;

public interface CommonService<E extends AbstractDocument> {
    List<E> getAll();

    E getById(String id);

    List<E> create(E e);

    E update(String id, E e);

    List<E> deleteById(String id);

    List<E> deleteAll();
}
