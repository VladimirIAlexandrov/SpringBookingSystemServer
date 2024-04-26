package com.example.controller;

import com.example.JwtResponse;
import com.example.JwtTokenProvider;
import com.example.LoginRequest;
import com.example.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private  ClientService userService;
    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtTokenProvider.createToken(authentication);

            Object user = userService.findByUsername(loginRequest.getUsername()).map(u -> {
                return Map.of("id", u.getId(), "roles", u.getRoles());
            }).orElse(null);

            return ResponseEntity.ok(List.of(new JwtResponse(jwt), user));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body("Неверное имя пользователя или пароль");
        }
    }
}

