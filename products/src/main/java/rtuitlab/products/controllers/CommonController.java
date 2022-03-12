package rtuitlab.products.controllers;

import org.springframework.web.bind.annotation.*;
import rtuitlab.products.dto.*;
import rtuitlab.products.entities.AbstractEntity;

import java.util.List;

public interface CommonController<E extends AbstractEntity, Get extends AbstractGetDTO, Post extends AbstractPostDTO, Put extends AbstractPutDTO, Posted extends AbstractPostedDTO, Updated extends AbstractUpdatedDTO> {
    @GetMapping("/")
    List<Get> getAll();

    @GetMapping("/{id}")
    Get getById(@PathVariable int id);

    @PostMapping("/")
    List<Posted> create(@RequestBody Post p);

    @PutMapping("/{id}")
    Updated update(@PathVariable int id, @RequestBody Put p);

    @DeleteMapping("/{id}")
    List<Get> deleteById(@PathVariable int id);

    @DeleteMapping("/")
    List<Get> deleteAll();
}
