package rtuitlab.deliveries.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import rtuitlab.deliveries.dto.user.UserInfoDTO;
import rtuitlab.deliveries.dto.user.UserLoginDTO;
import rtuitlab.deliveries.dto.user.UserLoginResponseDTO;
import rtuitlab.deliveries.dto.user.UserRegisterDTO;
import rtuitlab.deliveries.exceptions.InvalidCredentialsException;
import rtuitlab.deliveries.services.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;
    @PostMapping("/register")
    public UserInfoDTO register(@RequestBody UserRegisterDTO userRegisterDTO) {
        return userService.register(userRegisterDTO);
    }
    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO userLoginDTO) {
        try {
            return userService.authorize(userLoginDTO);
        } catch (InvalidCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }
    }
}
