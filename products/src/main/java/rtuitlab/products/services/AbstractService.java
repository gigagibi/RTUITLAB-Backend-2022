package rtuitlab.products.services;

import lombok.AllArgsConstructor;
import rtuitlab.products.entities.AbstractEntity;
import rtuitlab.products.exception.EntityNotFoundException;
import rtuitlab.products.mapper.CommonMapper;
import rtuitlab.products.repositories.CommonRepository;
import rtuitlab.products.dto.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AbstractService<E extends AbstractEntity, R extends CommonRepository<E>, Get extends AbstractGetDTO, Post extends AbstractPostDTO, Put extends AbstractPutDTO, Posted extends AbstractPostedDTO, Updated extends AbstractUpdatedDTO, M extends CommonMapper<E, Get, Post, Put, Posted, Updated>> implements CommonService<E, Get, Post, Put, Posted, Updated> {
    protected final R repository;
    protected final M mapper;
    @Override
    public List<Get> getAll() {
        return repository.findAll().stream().map(mapper::entityToGetDTO).collect(Collectors.toList());
    }

    @Override
    public Get getById(int id) throws EntityNotFoundException {
        return mapper.entityToGetDTO(repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id)));
    }

    @Override
    public List<Posted> create(Post p) {
        repository.save(mapper.postDTOToEntity(p));
        return repository.findAll().stream().map(mapper::entityToPostedDTO).collect(Collectors.toList());
    }

    @Override
    public Updated update(int id, Put p) throws EntityNotFoundException {
        if(!repository.existsById(id))
            throw new EntityNotFoundException(id);
        E e = mapper.putDTOToEntity(p);
        e.setId(id);
        repository.save(e);
        return mapper.entityToUpdatedDTO(repository.findById(id).orElse(null));
    }

    @Override
    public List<Get> deleteById(int id) throws EntityNotFoundException {
        if(!repository.existsById(id))
            throw new EntityNotFoundException(id);
        repository.deleteById(id);
        return repository.findAll().stream().map(mapper::entityToGetDTO).collect(Collectors.toList());
    }

    @Override
    public List<Get> deleteAll() {
        repository.deleteAll();
        return repository.findAll().stream().map(mapper::entityToGetDTO).collect(Collectors.toList());
    }
}
