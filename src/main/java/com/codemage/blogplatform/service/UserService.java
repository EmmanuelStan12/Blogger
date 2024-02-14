package com.codemage.blogplatform.service;

import com.codemage.blogplatform.dto.UserDTO;
import com.codemage.blogplatform.models.User;
import com.codemage.blogplatform.repositories.RoleRepository;
import com.codemage.blogplatform.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("prototype")
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(UserDTO userDTO) {
        User user = userDTO.toUser();
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRoles(roleRepository.getDefaultRoles().orElse(null));
        userRepository.save(user);
        return user;
    }
}
