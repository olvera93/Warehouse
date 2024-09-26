package com.olvera.warehouse.repository;

import com.olvera.warehouse.model.AppRole;
import com.olvera.warehouse.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(AppRole appRole);
}
