package com.starter.supplychainblockchain.repositories;
import com.starter.supplychainblockchain.models.authentication.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthenticationRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);
}
