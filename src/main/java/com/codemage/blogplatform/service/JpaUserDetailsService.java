package com.codemage.blogplatform.service;

import com.codemage.blogplatform.models.User;
import com.codemage.blogplatform.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.Optional;

public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.getUserByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Username or email not found");
        }
        return user.get().toUserDetails();
    }
}
