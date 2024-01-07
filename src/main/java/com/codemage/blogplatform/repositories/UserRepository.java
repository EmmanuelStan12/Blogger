package com.codemage.blogplatform.repositories;

import com.codemage.blogplatform.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> getUserByUsername(String username);

    Optional<User> getUserByEmail(String email);
}
