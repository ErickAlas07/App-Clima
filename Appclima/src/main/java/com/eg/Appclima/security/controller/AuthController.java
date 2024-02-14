package com.eg.Appclima.security.controller;

import com.eg.Appclima.security.dto.LoginUsuarioDto;
import com.eg.Appclima.security.dto.NuevoUsuarioDto;
import com.eg.Appclima.security.dto.RepuestaLoginDto;
import com.eg.Appclima.security.entity.Usuario;
import com.eg.Appclima.security.service.AutenticacionService;
import com.eg.Appclima.security.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AutenticacionService autService;

    @Autowired
    private JwtService jwtService;

    @Operation(summary = "Registrar un usuario nuevo.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario creado exitosamente.",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))}),
        @ApiResponse(responseCode = "400", description = "Datos incorrectos.",
                content = @Content),
        @ApiResponse(responseCode = "409", description = "Nombre de usuario no disponible.",
                content = @Content),
        @ApiResponse(responseCode = "500", description = "Error interno.",
                content = @Content)})
    @PostMapping("/registro")
    public ResponseEntity<Usuario> register(@RequestBody NuevoUsuarioDto nuevoUsuarioDto) {
        Usuario nuevoRegistro = autService.registrar(nuevoUsuarioDto);
        return ResponseEntity.ok(nuevoRegistro);
    }

    @Operation(summary = "Inicio de sesión & generación de token")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inicio de sesión correcto.",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))}),
        @ApiResponse(responseCode = "401", description = "Datos incorrectos.",
                content = @Content),
        @ApiResponse(responseCode = "404", description = "Usuario no ha sido encontrado.",
                content = @Content),
        @ApiResponse(responseCode = "500", description = "Error interno.",
                content = @Content)})
    @PostMapping("/login")
    public ResponseEntity<RepuestaLoginDto> autenticar(@RequestBody LoginUsuarioDto loginUsuario) {
        Usuario usuarioAut = autService.signin(loginUsuario);

        String jwtToken = jwtService.generateToken(usuarioAut);

        RepuestaLoginDto respuesta = new RepuestaLoginDto();
        respuesta.setToken(jwtToken);
        respuesta.setExpirado(jwtService.getExpirationTime());

        return ResponseEntity.ok(respuesta);
    }
}
