package com.hs.employees.repository;

import com.hs.employees.entity.UserActivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserActivoRepository extends JpaRepository<UserActivo, Long> {
    Optional<UserActivo> findByUsername(String username);
}
