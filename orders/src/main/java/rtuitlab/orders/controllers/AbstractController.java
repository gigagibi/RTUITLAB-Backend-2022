package rtuitlab.orders.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import rtuitlab.orders.exceptions.EntityNotFoundException;
import rtuitlab.orders.models.documents.AbstractDocument;
import rtuitlab.orders.services.CommonService;
import rtuitlab.orders.dto.*;

import java.util.List;

@AllArgsConstructor
public abstract class AbstractController<E extends AbstractDocument, Get extends AbstractGetDTO, Post extends AbstractPostDTO, Put extends AbstractPutDTO, Posted extends AbstractPostedDTO, Updated extends AbstractUpdatedDTO, S extends CommonService<E, Get, Post, Put, Posted, Updated>> implements CommonController<E, Get, Post, Put, Posted, Updated> {
    protected final S service;

    @Override
    public List<Get> getAll() {
        return service.getAll();
    }

    @Override
    public Get getById(String id) {
        try {
            return service.getById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Override
    public List<Posted> create(Post p) {
        return service.create(p);
    }

    @Override
    public Updated update(String id, Put p) {
        try {
            return service.update(id, p);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Override
    public List<Get> deleteById(String id) {
        try {
            return service.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Override
    public List<Get> deleteAll() {
        return null;
    }
}
