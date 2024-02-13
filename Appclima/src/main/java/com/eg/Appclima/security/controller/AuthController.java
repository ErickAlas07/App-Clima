package com.eg.Appclima.security.controller;

import com.eg.Appclima.security.dto.LoginUsuarioDto;
import com.eg.Appclima.security.dto.NuevoUsuarioDto;
import com.eg.Appclima.security.dto.RepuestaLoginDto;
import com.eg.Appclima.security.entity.Usuario;
import com.eg.Appclima.security.service.AutenticacionService;
import com.eg.Appclima.security.service.JwtService;
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

    @PostMapping("/registrar")
    public ResponseEntity<Usuario> register(@RequestBody NuevoUsuarioDto nuevoUsuarioDto) {
        Usuario nuevoRegistro = autService.registrar(nuevoUsuarioDto);
        return ResponseEntity.ok(nuevoRegistro);
    }
    
    @PostMapping("/login")
    public ResponseEntity<RepuestaLoginDto> autenticar(@RequestBody LoginUsuarioDto loginUsuario){
        Usuario usuarioAut = autService.signin(loginUsuario);
        
        String jwtToken = jwtService.generateToken(usuarioAut);
        
        RepuestaLoginDto respuesta = new RepuestaLoginDto();
        respuesta.setToken(jwtToken);
        respuesta.setExpirado(jwtService.getExpirationTime());
        
        return ResponseEntity.ok(respuesta);
    }
}
