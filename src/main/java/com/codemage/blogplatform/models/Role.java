package com.codemage.blogplatform.models;

import com.codemage.blogplatform.utils.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@Table(name = "Role")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private Boolean isDefault;

    public Role(String name, Status status, Boolean isDefault) {
        this.name = name;
        this.status = status;
        this.isDefault = isDefault;
    }

    public GrantedAuthority toGrantedAuthority() {
        return new SimpleGrantedAuthority(this.name);
    }

}
