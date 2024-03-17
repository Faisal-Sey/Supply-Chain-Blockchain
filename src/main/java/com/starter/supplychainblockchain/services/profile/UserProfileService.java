package com.starter.supplychainblockchain.services.profile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starter.supplychainblockchain.dtos.profile.AddProfileDTO;
import com.starter.supplychainblockchain.dtos.profile.GetProfileDTO;
import com.starter.supplychainblockchain.models.authentication.User;
import com.starter.supplychainblockchain.models.profile.Profile;
import com.starter.supplychainblockchain.repositories.AuthenticationRepository;
import com.starter.supplychainblockchain.repositories.UserProfileRepository;
import com.starter.supplychainblockchain.utilities.APIResponse;
import com.starter.supplychainblockchain.utilities.AppHelpers;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;


@Service
public class UserProfileService {
    private final APIResponse apiResponse;
    private final AuthenticationRepository authenticationRepository;
    private final UserProfileRepository userProfileRepository;
    private final AppHelpers appHelpers;
    private final ObjectMapper objectMapper;

    public UserProfileService(
            APIResponse apiResponse,
            AuthenticationRepository authenticationRepository,
            UserProfileRepository userProfileRepository,
            AppHelpers appHelpers, ObjectMapper objectMapper
    ) {
        this.apiResponse = apiResponse;
        this.authenticationRepository = authenticationRepository;
        this.userProfileRepository = userProfileRepository;
        this.appHelpers = appHelpers;
        this.objectMapper = objectMapper;
    }

    public ResponseEntity<Map<String, Object>> getSingleUserProfile() {
        String username = appHelpers.getAuthenticatedUser();
        Optional<Profile> optionalProfile = userProfileRepository.findByUser_Username(
                username
        );
        if (optionalProfile.isPresent()) {
            Profile profile = optionalProfile.get();
            GetProfileDTO profileResponse = objectMapper.convertValue(profile, GetProfileDTO.class);
            return apiResponse.success(
                    "Profile retrieved successfully",
                    profileResponse
            );
        } else {
            return apiResponse.error(
                    "Profile does not exist"
            );
        }
    }

    public ResponseEntity<Map<String, Object>> addUserProfile(AddProfileDTO requestBody) {
        String username = appHelpers.getAuthenticatedUser();
        Optional<User> optionalUser = authenticationRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Optional<Profile> optionalProfile = userProfileRepository.findByUser_Username(
                    username
            );
            if (optionalProfile.isPresent()) {
                return apiResponse.error("Profile already exists");
            }
            Profile profile = objectMapper.convertValue(requestBody, Profile.class);
            profile.setUser(user);
            Profile savedProfile = userProfileRepository.save(profile);
            return apiResponse.created(
                    "Profile created successfully",
                    savedProfile
            );
        } else {
            return apiResponse.error(
                    "User not found"
            );
        }
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
