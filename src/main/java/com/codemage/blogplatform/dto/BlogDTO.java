package com.codemage.blogplatform.dto;

import com.codemage.blogplatform.models.Blog;
import com.codemage.blogplatform.models.User;
import com.codemage.blogplatform.validators.annotations.ValidEmail;
import com.codemage.blogplatform.validators.annotations.ValidFile;
import com.codemage.blogplatform.validators.annotations.ValidPassword;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Getter
@Setter
public class BlogDTO {

    @NotEmpty(message = "Title must not be empty")
    private String title;

    @NotEmpty(message = "Content must not be empty")
    private String content;

    private String additionalInformation;

    @ValidFile
    private MultipartFile image;

    public Blog toBlog() throws IOException {
        return new Blog(title, content, additionalInformation, image.getBytes());
    }
}
