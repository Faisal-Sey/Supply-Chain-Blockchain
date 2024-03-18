package com.starter.supplychainblockchain.controllers.profile;

import com.starter.supplychainblockchain.dtos.profile.AddProfileDTO;
import com.starter.supplychainblockchain.services.profile.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("api/v1/profile")
public class UserProfileController {

    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/get-user-profile")
    public ResponseEntity<Map<String, Object>> getUserProfileController() {
        return userProfileService.getSingleUserProfile();
    }

    @PostMapping("/add-user-profile")
    public ResponseEntity<Map<String, Object>> addUserProfileController(
            @RequestBody AddProfileDTO requestBody
    ) {
        return userProfileService.addUserProfile(requestBody);
    }

    @PatchMapping("/update-user-profile")
    public ResponseEntity<Map<String, Object>> updateUserProfileController() {
        return userProfileService.updateUserProfile();
    }

    @GetMapping("/get-user-profiles")
    public ResponseEntity<Map<String, Object>> getUserProfilesController() {
        return userProfileService.getUserProfiles();
    }

    @DeleteMapping("/delete-user-profile")
    public ResponseEntity<Map<String, Object>> deleteUserProfileController() {
        return userProfileService.deleteUserProfile();
    }
}
