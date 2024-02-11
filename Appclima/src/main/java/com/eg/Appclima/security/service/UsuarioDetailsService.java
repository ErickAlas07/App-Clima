package com.eg.Appclima.security.service;

import com.eg.Appclima.security.entity.Usuario;
import com.eg.Appclima.security.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UsuarioDetailsService implements UserDetailsService{
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Optional<Usuario> opcional = usuarioRepository.findByUsername(username);
        
        if(!opcional.isPresent()){
            throw new UsernameNotFoundException("No se ha encontrado usuario");
        }
        return opcional.get();
    }
}
