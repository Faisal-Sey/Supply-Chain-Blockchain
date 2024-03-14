package com.starter.supplychainblockchain.controllers.profile;

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
        return this.userProfileService.getSingleUserProfile();
    }

    @PostMapping("/add-user-profile")
    public ResponseEntity<Map<String, Object>> addUserProfileController() {
        return this.userProfileService.addUserProfile();
    }

    @PatchMapping("/update-user-profile")
    public ResponseEntity<Map<String, Object>> updateUserProfileController() {
        return this.userProfileService.updateUserProfile();
    }

    @GetMapping("/get-user-profiles")
    public ResponseEntity<Map<String, Object>> getUserProfilesController() {
        return this.userProfileService.getUserProfiles();
    }

    @DeleteMapping("/delete-user-profile")
    public ResponseEntity<Map<String, Object>> deleteUserProfileController() {
        return this.userProfileService.deleteUserProfile();
    }
}
