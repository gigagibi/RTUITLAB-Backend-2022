package rtuitlab.supplies.services;

import lombok.AllArgsConstructor;
import rtuitlab.supplies.dto.AbstractGetDTO;
import rtuitlab.supplies.dto.AbstractPostPutDTO;
import rtuitlab.supplies.mappers.CommonMapper;
import rtuitlab.supplies.models.AbstractDocument;
import rtuitlab.supplies.repositories.CommonRepository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AbstractService<E extends AbstractDocument, R extends CommonRepository<E>, G extends AbstractGetDTO, P extends AbstractPostPutDTO, M extends CommonMapper<E, G, P>> implements CommonService<E, G, P> {

    protected final R repository;
    protected final M mapper;
    @Override
    public List<G> getAll() {
        return repository.findAll().stream().map(mapper::entityToDTO).collect(Collectors.toList());
    }

    @Override
    public G getById(String id) {
        return mapper.entityToDTO(repository.findById(id).orElse(null));
    }

    @Override
    public List<G> create(P p) {
        repository.save(mapper.postPutDTOToEntity(p));
        return repository.findAll().stream().map(mapper::entityToDTO).collect(Collectors.toList());
    }

    @Override
    public G update(String id, P p) {
        E e = mapper.postPutDTOToEntity(p);
        e.setId(id);
        repository.save(e);
        return mapper.entityToDTO(repository.findById(id).orElse(null));
    }

    @Override
    public List<G> deleteById(String id) {
        repository.deleteById(id);
        return repository.findAll().stream().map(mapper::entityToDTO).collect(Collectors.toList());
    }

    @Override
    public List<G> deleteAll() {
        repository.deleteAll();
        return repository.findAll().stream().map(mapper::entityToDTO).collect(Collectors.toList());
    }
}
