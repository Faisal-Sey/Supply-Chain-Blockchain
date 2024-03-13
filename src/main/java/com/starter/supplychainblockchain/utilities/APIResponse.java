package com.starter.supplychainblockchain.utilities;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class APIResponse {
    public static ResponseEntity<Map<String, Object>> generateResponse (HttpStatus statusCode, String message, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", statusCode);
        response.put("message", message);
        response.put("data", data);

        return new ResponseEntity<>(response, statusCode);
    }

    public  ResponseEntity<Map<String, Object>> success (String message) {
        return generateResponse(HttpStatus.OK, message, null);
    }

    public ResponseEntity<Map<String, Object>> success (String message, Object data) {
        return generateResponse(HttpStatus.OK, message, data);
    }

    public ResponseEntity<Map<String, Object>> created (String message, Object data) {
        return generateResponse(HttpStatus.CREATED, message, data);
    }

    public ResponseEntity<Map<String, Object>> error (String message) {
        return generateResponse(HttpStatus.BAD_REQUEST, message, null);
    }

    public ResponseEntity<Map<String, Object>> methodNotAllowed (String message) {
        return generateResponse(HttpStatus.METHOD_NOT_ALLOWED, message, null);
    }

    public ResponseEntity<Map<String, Object>> error (String message, Object data) {
        return generateResponse(HttpStatus.BAD_REQUEST, message, data);
    }

    public ResponseEntity<Map<String, Object>> notFound(String message) {
        return generateResponse(HttpStatus.NOT_FOUND, message, null);
    }
}
