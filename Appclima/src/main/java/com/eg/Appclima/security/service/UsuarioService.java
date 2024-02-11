package com.eg.Appclima.security.service;

import com.eg.Appclima.security.entity.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {
    
    public Usuario authUsuario(){
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if(authentication == null || authentication.isAuthenticated()){
            return null;
        }
        
        return (Usuario) authentication.getPrincipal();
    }
}
