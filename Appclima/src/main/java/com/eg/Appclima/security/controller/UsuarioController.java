package com.eg.Appclima.security.controller;

import com.eg.Appclima.security.entity.Usuario;
import com.eg.Appclima.security.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Obtener usuario actual.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario en el sistema.",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))}),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado.",
                content = @Content),
        @ApiResponse(responseCode = "403", description = "Usuario no autenticado.",
                content = @Content),
        @ApiResponse(responseCode = "500", description = "Error interno.",
                content = @Content)})
    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Usuario> autenticarUsuario() {
        Usuario actualUsuario = usuarioService.authUsuario();
        return ResponseEntity.ok(actualUsuario);
    }
}
