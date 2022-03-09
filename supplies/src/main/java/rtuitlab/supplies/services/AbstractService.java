package rtuitlab.supplies.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import rtuitlab.supplies.models.AbstractDocument;
import rtuitlab.supplies.repositories.CommonRepository;

import java.util.List;

@AllArgsConstructor
public class AbstractService<E extends AbstractDocument, R extends CommonRepository<E>> implements CommonService<E> {

    protected final R repository;
    @Override
    public List<E> getAll() {
        return repository.findAll();
    }

    @Override
    public E getById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<E> create(E e) {
        repository.save(e);
        return repository.findAll();
    }

    @Override
    public E update(String id, E e) {
        e.setId(id);
        repository.save(e);
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<E> deleteById(String id) {
        repository.deleteById(id);
        return repository.findAll();
    }

    @Override
    public List<E> deleteAll() {
        repository.deleteAll();
        return repository.findAll();
    }
}
