package com.SpringExaminationSystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SpringExaminationSystem.model.dto.request.auth.LoginRequest;
import com.SpringExaminationSystem.model.dto.request.auth.RegisterRequest;
import com.SpringExaminationSystem.model.entity.user.AuthInfo;
import com.SpringExaminationSystem.model.entity.user.User;
import com.SpringExaminationSystem.repository.user.AuthInfoDao;
import com.SpringExaminationSystem.repository.user.UserDao;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserDao userdao;
    private final AuthInfoDao authInfoDao;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (authInfoDao.existsByUserName(request.getUserName())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        // Create a new user
        User user = new User(request.getFirstName(), request.getLastName(), request.getEmail());
        userdao.save(user);
        // Create a new auth info
        AuthInfo authInfo = AuthInfo.builder()
                .userId(user.getUserId())
                .userName(request.getUserName())
                .password(passwordEncoder.encode(request.getPassword()))
                .user(user)
                .role(AuthInfo.ROLE_USER)
                .build();
        authInfoDao.save(authInfo);

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletRequest httpRequest) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUserName(), request.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(auth);
            httpRequest.getSession(true); // tạo session

            return ResponseEntity.ok("Login successful");

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}
