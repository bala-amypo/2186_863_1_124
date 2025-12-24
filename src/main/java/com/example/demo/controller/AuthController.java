package com.example.demo.controller;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.UserAccountService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserAccountService userAccountService;
    private final UserAccountRepository userAccountRepository;

    public AuthController(UserAccountService userAccountService,
                          UserAccountRepository userAccountRepository) {
        this.userAccountService = userAccountService;
        this.userAccountRepository = userAccountRepository;
    }

    // 🔹 Register
    @PostMapping("/register")
    public ResponseEntity<UserAccount> register(
            @RequestBody UserAccount user) {

        return new ResponseEntity<>(
                userAccountService.createUser(user),
                HttpStatus.CREATED
        );
    }

    // 🔹 Login (simple, no JWT)
    @PostMapping("/login")
    public ResponseEntity<UserAccount> login(
            @RequestParam String email,
            @RequestParam String password) {

        UserAccount user = userAccountRepository.findByEmail(email)
                .orElseThrow(() ->
                        new EntityNotFoundException("Invalid email or password"));

        if (!user.getPassword().equals(password)) {
            throw new EntityNotFoundException("Invalid email or password");
        }

        return ResponseEntity.ok(user);
    }
}
