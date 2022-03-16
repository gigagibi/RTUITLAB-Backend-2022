package rtuitlab.deliveries.services;

import com.auth0.jwt.JWT;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rtuitlab.deliveries.dto.user.UserInfoDTO;
import rtuitlab.deliveries.dto.user.UserRegisterDTO;
import rtuitlab.deliveries.dto.user.UserUpdateDTO;
import rtuitlab.deliveries.entities.UserEntity;
import rtuitlab.deliveries.exceptions.EntityNotFoundException;
import rtuitlab.deliveries.mappers.UserEntityMapper;
import rtuitlab.deliveries.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private UserEntityMapper userEntityMapper;
    private PasswordEncoder passwordEncoder;

    public List<UserInfoDTO> register(UserRegisterDTO userRegisterDTO) {
        userRegisterDTO.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        UserEntity userEntity = userEntityMapper.registerDTOToEntity(userRegisterDTO);
        userRepository.save(userEntity);
        return userRepository.findAll().stream().map(userEntityMapper::entityToInfoDTO).collect(Collectors.toList());
    }

    public UserInfoDTO update(int id, UserUpdateDTO userUpdateDTO) throws EntityNotFoundException {
        userUpdateDTO.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
        UserEntity userEntity = userEntityMapper.updateDTOToEntity(userUpdateDTO);
        userEntity.setId(id);
        userRepository.save(userEntity);
        return userEntityMapper.entityToInfoDTO(userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id)));
    }

    public List<UserInfoDTO> findAll() {
        return userRepository.findAll().stream().map(userEntityMapper::entityToInfoDTO).collect(Collectors.toList());
    }

    public UserInfoDTO getByToken(String token) {
        String username = JWT.decode(token.substring(7)).getSubject();
        UserEntity userEntity = userRepository.getByUsername(username);
        return userEntityMapper.entityToInfoDTO(userEntity);
    }
}
