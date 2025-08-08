package com.SpringExaminationSystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SpringExaminationSystem.model.dto.auth.LoginRequest;
import com.SpringExaminationSystem.model.dto.auth.RegisterRequest;
import com.SpringExaminationSystem.repository.user.AuthInfoDao;
import com.SpringExaminationSystem.repository.user.UserDao;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    // private final AuthenticationManager authenticationManager;
    private final UserDao userdao;
    // private final PasswordEncoder passwordEncoder;
    private final AuthInfoDao authInfoDao;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        // Check if the user already exists

        // Create a new user
        // Create a new auth info

        // Logic for user registration
        // Validate the request, encode the password, save the user, etc.
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletRequest httpRequest) {
        // Logic for user login
        // Authenticate the user, generate JWT token, etc.
        return ResponseEntity.ok("User logged in successfully");
    }

}
