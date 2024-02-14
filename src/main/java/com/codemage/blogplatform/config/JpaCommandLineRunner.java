package com.codemage.blogplatform.config;

import com.codemage.blogplatform.controllers.UserController;
import com.codemage.blogplatform.dto.UserDTO;
import com.codemage.blogplatform.models.Blog;
import com.codemage.blogplatform.models.Role;
import com.codemage.blogplatform.repositories.BlogRepository;
import com.codemage.blogplatform.repositories.RoleRepository;
import com.codemage.blogplatform.service.UserService;
import com.codemage.blogplatform.utils.Roles;
import com.codemage.blogplatform.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JpaCommandLineRunner implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        Role role = new Role(Roles.ADMIN, Status.ACTIVE, true);
        roleRepository.save(role);
        UserDTO userDTO = new UserDTO("Emmanuel", "Stanley", "Password12@", "emmanuelstanley753@gmail.com", "Emmanuel12");
        var user = userService.register(userDTO);
        Blog blog = new Blog("Title", "Content", "additional information", null);
        Blog blog2 = new Blog("Title", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                "additional information", null);
        blog.setUser(user);
        blog2.setUser(user);
        blogRepository.save(blog);
        blogRepository.save(blog2);
    }
}
