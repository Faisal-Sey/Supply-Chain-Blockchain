package com.starter.supplychainblockchain.services;

import com.starter.supplychainblockchain.controllers.authentication.AuthenticationRequest;
import com.starter.supplychainblockchain.controllers.authentication.RegisterRequest;
import com.starter.supplychainblockchain.dtos.authentication.RefreshTokenDTO;
import com.starter.supplychainblockchain.repositories.AuthenticationRepository;
import com.starter.supplychainblockchain.models.authentication.User;
import com.starter.supplychainblockchain.utilities.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthenticationService {
    private final AuthenticationRepository authenticationRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final APIResponse apiResponse;

    public AuthenticationService(
            AuthenticationRepository authenticationRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager, APIResponse apiResponse
    ) {
        this.authenticationRepository = authenticationRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.apiResponse = apiResponse;
    }

    public Map<String, String> generateTokens(User user) {
        Integer accessTokenExpiryTime = 1000 * 60 * 24;
        var accessToken = jwtService.generateToken(user, accessTokenExpiryTime);

        Integer refreshTokenExpiryTime = 1000 * 60 * 24 * 2;
        var refreshToken = jwtService.generateToken(user, refreshTokenExpiryTime);

        Map<String, String> responseData = new HashMap<>();
        responseData.put("accessToken", accessToken);
        responseData.put("refreshToken", refreshToken);
        return responseData;
    }

    public Map<String, String> register(RegisterRequest request) {
        var user = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .isAdmin(false)
                .role(request.getRole())
                .build();
        authenticationRepository.save(user);

        return generateTokens(user);
    }

    public Map<String, String> authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = authenticationRepository.findByUsername(request.getUsername()).orElseThrow();
        return generateTokens(user);
    }

    public ResponseEntity<Map<String, Object>> refresh(RefreshTokenDTO request) {
        String refreshToken = request.getRefreshToken();
        String username = jwtService.extractUsername(refreshToken);
        if (username != null) {
            Optional<User> optionalUser = authenticationRepository.findByUsername(username);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                Map<String, String> responseData = generateTokens(user);
                return apiResponse.success("User token refresh successful", responseData);
            } else {
               return apiResponse.error("Incorrect token provided");
            }
        } else {
            return apiResponse.error("Incorrect token provided");

        }
    }

}
