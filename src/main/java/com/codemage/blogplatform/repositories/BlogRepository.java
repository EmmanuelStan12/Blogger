package com.codemage.blogplatform.repositories;

import com.codemage.blogplatform.models.Blog;
import com.codemage.blogplatform.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface BlogRepository extends CrudRepository<Blog, Long> {

    @Query("SELECT b FROM Blog b WHERE (b.title LIKE %:searchQuery% OR b.additionalInformation LIKE %:searchQuery% OR b.content LIKE %:searchQuery%) AND b.user.username = :username")
    List<Blog> findBlogsBySearchQuery(@Param("searchQuery") String searchQuery, @Param("username") String username);

    @Query("SELECT b FROM Blog b WHERE b.id = :id AND b.user.username = :username")
    Optional<Blog> findBlogById(@Param("id") Long id, @Param("username") String username);
}
