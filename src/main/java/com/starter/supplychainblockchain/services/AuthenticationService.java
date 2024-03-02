package com.starter.supplychainblockchain.services;

import com.starter.supplychainblockchain.controllers.authentication.AuthenticationRequest;
import com.starter.supplychainblockchain.controllers.authentication.RegisterRequest;
import com.starter.supplychainblockchain.models.authentication.Role;
import com.starter.supplychainblockchain.repositories.AuthenticationRepository;
import com.starter.supplychainblockchain.models.authentication.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {
    private final AuthenticationRepository authenticationRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            AuthenticationRepository authenticationRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ) {
        this.authenticationRepository = authenticationRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public Map<String, Object> register(RegisterRequest request) {
        var user = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .isAdmin(false)
                .role(Role.USER)
                .build();
        authenticationRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("token", jwtToken);
        return responseData;
    }

    public Map<String, Object> authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = authenticationRepository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("token", jwtToken);
        return responseData;
    }
}
