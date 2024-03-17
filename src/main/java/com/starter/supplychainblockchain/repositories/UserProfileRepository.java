package com.starter.supplychainblockchain.repositories;

import com.starter.supplychainblockchain.models.profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByUser_Username(String username);
}
