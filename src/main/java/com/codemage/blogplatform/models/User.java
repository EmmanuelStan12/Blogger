package com.codemage.blogplatform.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "`User`")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column(unique = true)
    private String username;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Column
    @Temporal(TemporalType.DATE)
    private Date updatedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "User_Role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Blog> blogs = new HashSet<>();

    public User(String email, String password, String username, String firstname, String lastname) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFullName() {
        return firstname == null ? username : firstname + (lastname != null ? " " + lastname : "");
    }

    public org.springframework.security.core.userdetails.User toUserDetails() {
        return new org.springframework.security.core.userdetails.User(
                this.username,
                this.password,
                roles.stream().map(Role::toGrantedAuthority).toList()
        );
    }
}
