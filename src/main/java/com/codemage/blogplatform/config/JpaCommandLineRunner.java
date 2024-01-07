package com.codemage.blogplatform.config;

import com.codemage.blogplatform.models.Role;
import com.codemage.blogplatform.repositories.RoleRepository;
import com.codemage.blogplatform.utils.Roles;
import com.codemage.blogplatform.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JpaCommandLineRunner implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        Role role = new Role(Roles.ADMIN, Status.ACTIVE, true);
        roleRepository.save(role);
    }
}
