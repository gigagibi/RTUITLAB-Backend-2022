package rtuitlab.deliveries.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rtuitlab.deliveries.dto.user.*;
import rtuitlab.deliveries.entities.UserEntity;
import rtuitlab.deliveries.exceptions.EntityNotFoundException;
import rtuitlab.deliveries.exceptions.InvalidCredentialsException;
import rtuitlab.deliveries.mappers.UserEntityMapper;
import rtuitlab.deliveries.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserEntityMapper userEntityMapper;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserEntityMapper userEntityMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${jwt.secret}")
    private String jwtSecret;

    public UserInfoDTO register(UserRegisterDTO userRegisterDTO) {
        userRegisterDTO.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        UserEntity userEntity = userEntityMapper.registerDTOToEntity(userRegisterDTO);
        userRepository.save(userEntity);
        return userEntityMapper.entityToInfoDTO(userEntity);
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

    public UserLoginResponseDTO authorize(UserLoginDTO userLoginDTO) throws InvalidCredentialsException {
        UserLoginResponseDTO userLoginResponseDTO = null;
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();
        UserEntity userEntity = userRepository.getByUsername(username);
        if (userEntity!=null && BCrypt.checkpw(password, userEntity.getPassword())) {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            String userRole = userEntity.getRole().getName();
            String access_token = JWT.create().withSubject(username).withClaim("roles", List.of(userRole)).sign(algorithm);
            userLoginResponseDTO = new UserLoginResponseDTO(access_token, userRole);
            return userLoginResponseDTO;
        }
        else throw new InvalidCredentialsException();
    }

    public List<UserEntity> create(UserEntity userEntity) {
        userRepository.save(userEntity);
        return userRepository.findAll();
    }

    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    public List<UserEntity> deleteById(int id) {
        userRepository.deleteById(id);
        return userRepository.findAll();
    }

    public List<UserEntity> deleteAll() {
        userRepository.deleteAll();
        return userRepository.findAll();
    }
}
