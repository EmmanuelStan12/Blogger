package com.codemage.blogplatform.service;

import com.codemage.blogplatform.dto.BlogDTO;
import com.codemage.blogplatform.models.Blog;
import com.codemage.blogplatform.models.User;
import com.codemage.blogplatform.repositories.BlogRepository;
import com.codemage.blogplatform.repositories.RoleRepository;
import com.codemage.blogplatform.repositories.UserRepository;
import com.codemage.blogplatform.utils.Status;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    private final UserRepository userRepository;

    public BlogService(BlogRepository blogRepository, UserRepository userRepository) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
    }


    public void createBlog(BlogDTO blogDTO, String username) throws IOException {
        Blog blog = blogDTO.toBlog();
        blog.setStatus(Status.ACTIVE);
        blog.setUser(userRepository.getUserByUsername(username).orElseThrow());
        blogRepository.save(blog);
    }

    public List<Blog> searchBlog(String searchQuery, String username) {
        if (searchQuery == null) {
            searchQuery = "";
        }
        searchQuery = String.join("%", searchQuery.split(" "));
        return blogRepository.findBlogsBySearchQuery(searchQuery, username);
    }

    public Optional<Blog> findBlogById(Long id, String username) {
        return blogRepository.findBlogById(id, username);
    }
}
