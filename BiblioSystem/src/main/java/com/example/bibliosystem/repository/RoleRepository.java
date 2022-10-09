package com.example.bibliosystem.repository;

import java.util.Optional;

import com.example.bibliosystem.common.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.bibliosystem.entity.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
