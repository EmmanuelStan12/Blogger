package com.codemage.blogplatform.dto;

import com.codemage.blogplatform.models.User;
import com.codemage.blogplatform.validators.annotations.ValidEmail;
import com.codemage.blogplatform.validators.annotations.ValidPassword;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    @NotEmpty(message = "Firstname must not be empty")
    private String firstName;

    @NotEmpty(message = "Lastname must not be empty")
    private String lastName;

    @ValidPassword
    private String password;

    private String matchingPassword;

    @ValidEmail
    private String email;

    @NotEmpty(message = "Username must not be empty")
    private String username;

    public UserDTO(String firstName, String lastName, String password, String email, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.username = username;
    }

    public User toUser() {
        return new User(email, password, username, firstName, lastName);
    }
}
