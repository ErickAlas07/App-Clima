package com.eg.Appclima.service;

import com.eg.Appclima.entity.Registros;
import com.eg.Appclima.repository.RegistrosRepository;
import com.eg.Appclima.security.entity.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrosService {

    @Autowired
    private RegistrosRepository registrosRepository;

    public void registrarConsulta(Usuario usuario, String consulta, String respuesta) {
        Registros queryRegistros = new Registros();
        //queryRegistros.setId(Long.MIN_VALUE);
        queryRegistros.setUsuario(usuario);
        queryRegistros.setConsulta(consulta);
        queryRegistros.setRespuesta(respuesta);
        registrosRepository.save(queryRegistros);
    }

}
