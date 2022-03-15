package rtuitlab.supplies.services;

import lombok.AllArgsConstructor;
import rtuitlab.supplies.dto.*;
import rtuitlab.supplies.exceptions.EntityNotFoundException;
import rtuitlab.supplies.mappers.CommonMapper;
import rtuitlab.supplies.models.documents.AbstractDocument;
import rtuitlab.supplies.repositories.CommonRepository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AbstractService<E extends AbstractDocument, R extends CommonRepository<E>, Get extends AbstractGetDTO, Post extends AbstractPostDTO, Put extends AbstractPutDTO, Posted extends AbstractPostedDTO, Updated extends AbstractUpdatedDTO, M extends CommonMapper<E, Get, Post, Put, Posted, Updated>> implements CommonService<E, Get, Post, Put, Posted, Updated> {

    protected final R repository;
    protected final M mapper;
    @Override
    public List<Get> getAll() {
        return repository.findAll().stream().map(mapper::entityToGetDTO).collect(Collectors.toList());
    }

    @Override
    public Get getById(String id) throws EntityNotFoundException {
        return mapper.entityToGetDTO(repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id)));
    }

    @Override
    public List<Posted> create(Post p) {
        repository.save(mapper.postDTOToEntity(p));
        return repository.findAll().stream().map(mapper::entityToPostedDTO).collect(Collectors.toList());
    }

    @Override
    public Updated update(String id, Put p) throws EntityNotFoundException {
        if(!repository.existsById(id))
            throw new EntityNotFoundException(id);
        E e = mapper.putDTOToEntity(p);
        e.setId(id);
        repository.save(e);
        return mapper.entityToUpdatedDTO(repository.findById(id).orElse(null));
    }

    @Override
    public List<Get> deleteById(String id) throws EntityNotFoundException {
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
