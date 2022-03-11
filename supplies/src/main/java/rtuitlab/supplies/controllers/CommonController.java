package rtuitlab.supplies.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import rtuitlab.supplies.dto.AbstractGetDTO;
import rtuitlab.supplies.dto.AbstractPostPutDTO;
import rtuitlab.supplies.models.AbstractDocument;

import java.util.List;

public interface CommonController<E extends AbstractDocument, G extends AbstractGetDTO, P extends AbstractPostPutDTO> {
    @GetMapping("/")
    List<G> getAll();

    @GetMapping("/{id}")
    G getById(@PathVariable String id);

    @PostMapping("/")
    List<G> create(@RequestBody P p);

    @PutMapping("/{id}")
    G update(@PathVariable String id, @RequestBody P p);

    @DeleteMapping("/{id}")
    List<G> deleteById(@PathVariable String id);

    @DeleteMapping("/")
    List<G> deleteAll();
}
