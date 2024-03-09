package com.starter.supplychainblockchain.utilities;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    private final APIResponse apiResponse;

    public ExceptionHandlerAdvice(APIResponse apiResponse) {
        this.apiResponse = apiResponse;
    }

    @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class})
    ResponseEntity<Map<String, Object>> handleAuthenticationException() {
        return apiResponse.error("Username or Password Incorrect");
    }
}