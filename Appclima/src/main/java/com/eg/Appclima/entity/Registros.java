package com.eg.Appclima.entity;

import com.eg.Appclima.security.entity.Usuario;
import jakarta.persistence.*;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "registro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Registros implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private Usuario usuario;

    @Column()
    private String consulta;
    @Column()
    private String respuesta;

    public Registros(Usuario usuario, String consulta, String respuesta) {
        this.usuario = usuario;
        this.consulta = consulta;
        this.respuesta = respuesta;
    }

}
