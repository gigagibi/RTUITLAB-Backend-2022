package rtuitlab.supplies.controllers;

import org.springframework.web.bind.annotation.*;
import rtuitlab.supplies.dto.*;
import rtuitlab.supplies.models.AbstractDocument;

import java.util.List;

public interface CommonController<E extends AbstractDocument, Get extends AbstractGetDTO, Post extends AbstractPostDTO, Put extends AbstractPutDTO, Posted extends AbstractPostedDTO, Updated extends AbstractUpdatedDTO> {
    @GetMapping("/")
    List<Get> getAll();

    @GetMapping("/{id}")
    Get getById(@PathVariable String id);

    @PostMapping("/")
    List<Posted> create(@RequestBody Post p);

    @PutMapping("/{id}")
    Updated update(@PathVariable String id, @RequestBody Put p);

    @DeleteMapping("/{id}")
    List<Get> deleteById(@PathVariable String id);

    @DeleteMapping("/")
    List<Get> deleteAll();
}
