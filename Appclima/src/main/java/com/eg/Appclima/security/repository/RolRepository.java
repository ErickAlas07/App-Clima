package com.eg.Appclima.security.repository;

import com.eg.Appclima.security.entity.Rol;
import com.eg.Appclima.security.enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository <Rol, Long>{
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
