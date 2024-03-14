package com.starter.supplychainblockchain.utilities;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AppHelpers {
    public UserDetails getAuthenticatedUser() {

        // Retrieve the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        // Check if the authentication object is not null and contains the principal (user details)
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            return userDetails;
        } else {
            return null;
        }

    }
}
