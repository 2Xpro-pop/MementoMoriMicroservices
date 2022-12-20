package com.example.auth;

import com.example.auth.models.Role;
import com.example.auth.models.User;
import com.example.auth.services.AuthService;
import com.example.auth.services.PasswordHasher;
import com.example.auth.services.UserService;
import com.example.auth.viewmodels.*;
import jakarta.security.auth.message.AuthException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final PasswordHasher passwordHasher;

    @GetMapping("login/{login}/{password}")
    public ResponseEntity login(@PathVariable String login, @PathVariable String password) {
        final Optional<JwtResponse> token = authService.login(login, password);

        if(token.isEmpty())
        {
            return  ResponseEntity.badRequest().body("Wrong password or login");
        }

        return ResponseEntity.ok(token);
    }

    @PostMapping("create-user")
    public ResponseEntity login(@RequestBody UserViewModel userVm) {
        User user;
        try {
            user = new User(-1, userVm.getLogin(), userVm.getPassword(), Role.valueOf(userVm.getRole()), userVm.getBranchOfficeId());
        } catch (IllegalArgumentException exc) {
            return ResponseEntity.badRequest().body("Incorrect role!");
        }

        if (user.getLogin() == null) {
            return ResponseEntity.badRequest().body("Require param login, doesn't exist");
        }

        if (user.getPassword() == null) {
            return ResponseEntity.badRequest().body("Require param password, doesn't exist");
        }

        if (userService.getByLogin(user.getLogin()).isPresent()) {
            return ResponseEntity.badRequest().body("The user with same login already exist!");
        }

        userService.createUser(user, user.getPassword());
        return ResponseEntity.ok().build();
    }

    @PostMapping("token/{request}")
    public ResponseEntity getNewAccessToken(@PathVariable String request) {
        final JwtResponse token;
        try {
            token = authService.getAccessToken(request);
        } catch (AuthException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(token);
    }

    @PostMapping("refresh/{request}")
    public ResponseEntity getNewRefreshToken(@PathVariable String request) {
        final JwtResponse token;
        try {
            token = authService.refresh(request);
        } catch (AuthException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(token);
    }

}
