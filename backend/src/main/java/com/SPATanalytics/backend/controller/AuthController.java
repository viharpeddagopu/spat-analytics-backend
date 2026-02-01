package com.SPATanalytics.backend.controller;

import com.SPATanalytics.backend.model.JwtResponse;
import com.SPATanalytics.backend.model.LoginRequest;
import com.SPATanalytics.backend.model.User;
import com.SPATanalytics.backend.repository.UserRepository;
import com.SPATanalytics.backend.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // REGISTER (already working)
    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password) {

        if (userRepository.findByUsername(username).isPresent()) {
            return "Username already exists";
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);
        return "User registered successfully";
    }

    // LOGIN (NEW)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = JwtUtil.generateToken(user.getUsername());

        return ResponseEntity.ok(new JwtResponse(token));
    }

}