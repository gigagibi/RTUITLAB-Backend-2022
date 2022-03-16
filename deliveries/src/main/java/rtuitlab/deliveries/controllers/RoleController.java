package rtuitlab.deliveries.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rtuitlab.deliveries.entities.RoleEntity;
import rtuitlab.deliveries.services.RoleService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@PreAuthorize("hasRole('ADMIN')")
@AllArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping("/")
    public List<RoleEntity> getAll() {
        return roleService.getAll();
    }

    @PostMapping("/")
    public List<RoleEntity> create(@RequestBody RoleEntity roleEntity) {
        return roleService.create(roleEntity);
    }

    @PutMapping("/{id}")
    public RoleEntity updateById(@PathVariable int id, @RequestBody RoleEntity roleEntity) {
        return roleService.updateById(id, roleEntity);
    }

    @DeleteMapping("/")
    public List<RoleEntity> deleteAll() {
        return roleService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public List<RoleEntity> deleteById(@PathVariable int id) {
        return roleService.deleteById(id);
    }
}
