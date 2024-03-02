package com.starter.supplychainblockchain.controllers.authentication;

import com.starter.supplychainblockchain.services.AuthenticationService;
import com.starter.supplychainblockchain.utilities.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;


@Controller
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final APIResponse apiResponse;
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(
            APIResponse apiResponse,
            AuthenticationService authenticationService
    ) {
        this.apiResponse = apiResponse;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register (
            @RequestBody RegisterRequest request
    ) {
        Map<String, Object> responseData = authenticationService.register(request);
        return apiResponse.success(
                "User registered successfully",
                responseData
        );
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Map<String, Object>> register (
            @RequestBody AuthenticationRequest request
    ) {
        Map<String, Object> responseData = authenticationService.authenticate(request);
        return apiResponse.success(
                "User authenticated successfully",
                responseData
        );
    }
}
