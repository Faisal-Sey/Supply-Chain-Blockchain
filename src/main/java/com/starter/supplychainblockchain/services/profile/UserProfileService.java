package com.starter.supplychainblockchain.services.profile;

import com.starter.supplychainblockchain.utilities.APIResponse;
import com.starter.supplychainblockchain.utilities.AppHelpers;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class UserProfileService {
    private final APIResponse apiResponse;
    private final AppHelpers appHelpers;

    public UserProfileService(APIResponse apiResponse, AppHelpers appHelpers) {
        this.apiResponse = apiResponse;
        this.appHelpers = appHelpers;
    }

    public ResponseEntity<Map<String, Object>> getSingleUserProfile() {
        UserDetails userDetails = appHelpers.getAuthenticatedUser();
        if (userDetails == null) {
            return apiResponse.error("User is not authenticated");
        }
//        System.out.println(userDetails.);
        return apiResponse.success("");
    }

    public ResponseEntity<Map<String, Object>> addUserProfile() {
        return this.apiResponse.success("");
    }

    public ResponseEntity<Map<String, Object>> getUserProfiles() {
        return this.apiResponse.success("");
    }

    public ResponseEntity<Map<String, Object>> updateUserProfile() {
        return this.apiResponse.success("");
    }

    public ResponseEntity<Map<String, Object>> deleteUserProfile() {
        return this.apiResponse.success("");
    }
}
