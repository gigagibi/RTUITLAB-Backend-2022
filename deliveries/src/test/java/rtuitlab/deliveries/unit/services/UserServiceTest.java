package rtuitlab.deliveries.unit.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import rtuitlab.deliveries.dto.user.UserLoginDTO;
import rtuitlab.deliveries.dto.user.UserLoginResponseDTO;
import rtuitlab.deliveries.dto.user.UserRegisterDTO;
import rtuitlab.deliveries.entities.RoleEntity;
import rtuitlab.deliveries.entities.UserEntity;
import rtuitlab.deliveries.exceptions.InvalidCredentialsException;
import rtuitlab.deliveries.mappers.UserEntityMapper;
import rtuitlab.deliveries.repositories.RoleRepository;
import rtuitlab.deliveries.repositories.UserRepository;
import rtuitlab.deliveries.services.UserService;

import java.util.List;
import java.util.function.Supplier;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock private UserRepository mockRepository;
    @Mock private UserEntityMapper mockMapper;
    @Mock private RoleRepository roleRepository;
    private Supplier<RoleEntity> userRoleSupplier;
    private Supplier<UserRegisterDTO> registerDTOSupplier;
    private Supplier<UserLoginDTO> loginDTOSupplier;
    private PasswordEncoder passwordEncoder;
    private UserService userService;
    Supplier<UserEntity> userEntitySupplier;

    @BeforeEach
    public void setUp() {
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.userService = new UserService(mockRepository, mockMapper, passwordEncoder, roleRepository);
        this.userService.setJwtSecret("secret");
        this.userEntitySupplier = () -> new UserEntity(
                1,
                "user",
                "$2a$10$H6WtDuCC7kztAyq93YRSEOnQLpVIH19NXH2maV3dWJV3tbFS.sCHe",
                new RoleEntity(1, "ROLE_USER"),
                "user",
                "user",
                "user",
                "user"
        );
        this.userRoleSupplier = () -> new RoleEntity(
                1,"ROLE_USER"
        );
        this.registerDTOSupplier = () -> new UserRegisterDTO(
                "user",
                "user",
                "user",
                "user",
                "user",
                "user"
        );
        this.loginDTOSupplier = () -> new UserLoginDTO(
                "user",
                "user"
        );
    }




    @Test
    void shouldRegisterUser() {
        // arrange
        UserRegisterDTO userRegisterDTO = registerDTOSupplier.get();
        UserEntity userEntity = userEntitySupplier.get();
        String expectedPassword = userRegisterDTO.getPassword();
        given(mockMapper.registerDTOToEntity(userRegisterDTO)).willReturn(userEntity);
        given(roleRepository.findByName("ROLE_USER")).willReturn(userRoleSupplier.get());

        // act
        userService.register(userRegisterDTO);

        // assert
        ArgumentCaptor<UserEntity> userEntityArgumentCaptor = ArgumentCaptor.forClass(UserEntity.class);
        verify(mockRepository).save(userEntityArgumentCaptor.capture());
        UserEntity captured = userEntityArgumentCaptor.getValue();
        assertThat(captured.getUsername()).isEqualTo(userRegisterDTO.getUsername());
        assertThat(BCrypt.checkpw(expectedPassword, captured.getPassword())).isTrue();
        assertThat(captured.getRole().getName()).isEqualTo("ROLE_USER");
    }

    @Test
    void shouldGetByToken() {
        // arrange
        String secret = "secret";
        UserEntity userEntity = userEntitySupplier.get();
        String expectedUsername = userEntity.getUsername();
        String token = "Bearer " + JWT.create().withSubject(expectedUsername).withClaim("roles", List.of(userEntity.getRole().getName())).sign(Algorithm.HMAC256(secret));

        // act
        userService.getByToken(token);

        // assert
        ArgumentCaptor<String> usernameArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockRepository).getByUsername(usernameArgumentCaptor.capture());
        String captured = usernameArgumentCaptor.getValue();
        assertThat(captured).isEqualTo(expectedUsername);
    }

    @Test
    void shouldAuthorizeUser() throws InvalidCredentialsException {
        // arrange
        UserLoginDTO userLoginDTO = loginDTOSupplier.get();
        UserEntity userEntity = userEntitySupplier.get();
        given(mockRepository.getByUsername(userLoginDTO.getUsername()))
                .willReturn(userEntity);

        // act
        UserLoginResponseDTO userLoginResponseDTO = userService.authorize(userLoginDTO);

        // assert
        String decodedFromJWTUsername = JWT.decode(userLoginResponseDTO.getToken()).getSubject();
        assertThat(decodedFromJWTUsername).isEqualTo(userLoginDTO.getUsername());
    }

    @Test
    void shouldGetAllUsers() {
        // arrange (no given arrange)

        // act
        userService.getAll();

        // assert
        verify(mockRepository).findAll();
    }

    @Test
    void shouldCreateUser() {
        // arrange
        UserEntity userEntity = userEntitySupplier.get();

        // act
        userService.create(userEntity);

        // assert
        ArgumentCaptor<UserEntity> userEntityArgumentCaptor = ArgumentCaptor.forClass(UserEntity.class);
        verify(mockRepository).save(userEntityArgumentCaptor.capture());
        UserEntity captured = userEntityArgumentCaptor.getValue();
        assertThat(captured).isEqualTo(userEntity);
    }

    @Test
    void shouldDeleteById() {
        // arrange
        int id = userEntitySupplier.get().getId();

        // act
        userService.deleteById(id);

        // assert
        ArgumentCaptor<Integer> idArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(mockRepository).deleteById(idArgumentCaptor.capture());
        int captured = idArgumentCaptor.getValue();
        assertThat(captured).isEqualTo(id);
    }

    @Test
    void deleteAll() {
        // arrange (no given arrange)

        // act
        userService.deleteAll();

        // assert
        verify(mockRepository).deleteAll();
    }
}