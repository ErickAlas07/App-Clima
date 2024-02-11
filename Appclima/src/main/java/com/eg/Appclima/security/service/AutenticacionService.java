package com.eg.Appclima.security.service;

import com.eg.Appclima.security.dto.LoginUsuarioDto;
import com.eg.Appclima.security.dto.NuevoUsuarioDto;
import com.eg.Appclima.security.entity.Rol;
import com.eg.Appclima.security.entity.Usuario;
import com.eg.Appclima.security.enums.RolNombre;
import com.eg.Appclima.security.repository.RolRepository;
import com.eg.Appclima.security.repository.UsuarioRepository;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public Usuario registrar(NuevoUsuarioDto nuevoUsuario) {

        Optional<Rol> rol = rolRepository.findByRolNombre(RolNombre.USER);

        if (rol.isEmpty()) {
            throw new RuntimeException("Rol no encontrado.");
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(nuevoUsuario.getUsername());
        usuario.setPassword(passwordEncoder.encode(nuevoUsuario.getPassword()));
        usuario.setRol(rol.get());

        return usuarioRepository.save(usuario);
    }

    public Usuario signin(LoginUsuarioDto loginUsuario) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUsuario.getUsername(),
                        loginUsuario.getPassword()));

        return usuarioRepository.findByUsername(loginUsuario.getUsername()).orElseThrow(NoSuchElementException::new);
    }
}
