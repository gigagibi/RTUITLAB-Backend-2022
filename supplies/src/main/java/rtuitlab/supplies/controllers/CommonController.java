package rtuitlab.supplies.controllers;

import org.springframework.web.bind.annotation.*;
import rtuitlab.supplies.models.AbstractDocument;

import java.util.List;

public interface CommonController<E extends AbstractDocument> {
    @GetMapping("/")
    List<E> getAll();

    @GetMapping("/{id}")
    E getById(@PathVariable String id);

    @PostMapping("/")
    List<E> create(@RequestBody E e);

    @PutMapping("/{id}")
    E update(@PathVariable String id, @RequestBody E e);

    @DeleteMapping("/{id}")
    List<E> deleteById(@PathVariable String id);

    @DeleteMapping("/")
    List<E> deleteAll();
}
