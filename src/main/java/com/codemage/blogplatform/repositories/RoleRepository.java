package com.codemage.blogplatform.repositories;

import com.codemage.blogplatform.models.Role;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Transactional
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    @Query("SELECT r FROM Role r WHERE r.status = 'ACTIVE' AND r.isDefault = true")
    Optional<Set<Role>> getDefaultRoles();
}
