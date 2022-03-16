package rtuitlab.deliveries.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rtuitlab.deliveries.entities.RoleEntity;
import rtuitlab.deliveries.repositories.RoleRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public List<RoleEntity> getAll() {
        return roleRepository.findAll();
    }

    public List<RoleEntity> create(RoleEntity roleEntity) {
        roleRepository.save(roleEntity);
        return roleRepository.findAll();
    }

    public List<RoleEntity> deleteById(int id){
        roleRepository.deleteById(id);
        return roleRepository.findAll();
    }

    public List<RoleEntity> deleteAll() {
        roleRepository.deleteAll();
        return roleRepository.findAll();
    }

    public RoleEntity updateById(int id, RoleEntity roleEntity) {
        roleEntity.setId(id);
        roleRepository.save(roleEntity);
        return roleRepository.getById(id);
    }
}
