package com.eg.Appclima.security.controller;

import com.eg.Appclima.security.entity.Usuario;
import com.eg.Appclima.security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Usuario> autenticarUsuario() {
        Usuario actualUsuario = usuarioService.authUsuario();
        return ResponseEntity.ok(actualUsuario);
    }
}
