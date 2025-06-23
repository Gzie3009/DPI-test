package com.gzie.dpi.Controllers;


import com.gzie.dpi.DTOs.LoginRequestDTO;
import com.gzie.dpi.DTOs.LoginResponseDTO;
import com.gzie.dpi.Entities.UserEntity;
import com.gzie.dpi.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO body){
        LoginResponseDTO response = new LoginResponseDTO();

        try {
            UserEntity user = userService.createUserIfNotExist(body);

            response.setStatus(200);
            response.setEmail(user.getEmail());
            response.setSuccess(true);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.setStatus(500);
            response.setSuccess(false);
            response.setEmail(null);
            System.out.println("Login error: " + e.getMessage());

            return ResponseEntity.status(500).body(response);
        }
    }
}
