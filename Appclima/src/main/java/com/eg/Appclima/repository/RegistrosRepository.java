package com.eg.Appclima.repository;

import com.eg.Appclima.entity.Registros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrosRepository extends JpaRepository<Registros, Long> {
    
}
