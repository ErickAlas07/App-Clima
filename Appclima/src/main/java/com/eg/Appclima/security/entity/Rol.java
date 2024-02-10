package com.eg.Appclima.security.entity;

import com.eg.Appclima.security.enums.RolNombre;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rol implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    
    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private RolNombre rolNombre;
    
    public Rol(@NotNull RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }

    public String getAuthority(){
        return rolNombre.name();
    }      
}
