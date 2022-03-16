package rtuitlab.deliveries.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rtuitlab.deliveries.entities.UserEntity;
import rtuitlab.deliveries.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@PreAuthorize("hasRole('ADMIN')")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public List<UserEntity> getAll() {
        return userService.getAll();
    }

    @PostMapping("/")
    public List<UserEntity> create(@RequestBody UserEntity userEntity) {
        return userService.create(userEntity);
    }

    @DeleteMapping("/{id}")
    public List<UserEntity> deleteById(@PathVariable int id) {
        return userService.deleteById(id);
    }

    @DeleteMapping("/")
    public List<UserEntity> deleteAll() {
        return userService.deleteAll();
    }
}


