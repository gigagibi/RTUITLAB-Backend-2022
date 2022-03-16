package rtuitlab.deliveries.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import rtuitlab.deliveries.dto.user.UserLoginResponseDTO;

@RestController
public interface SwaggerFakeLogin {
    @ApiOperation(value = "Login", notes = "Login with the given credentials.")
    @ApiResponse(code = 200, message = "", response = UserLoginResponseDTO.class)
    @PostMapping("/api/v1/auth/login")
    default void login() {
        throw new IllegalStateException("Add Spring Security to handle authentication");
    }
}
