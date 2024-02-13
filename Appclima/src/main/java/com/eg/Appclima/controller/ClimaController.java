package com.eg.Appclima.controller;

import com.eg.Appclima.config.*;
import com.eg.Appclima.dto.AireContaminadoDto;
import com.eg.Appclima.dto.ClimaActualDto;
import com.eg.Appclima.dto.PronosticoDto;
import com.eg.Appclima.security.entity.Usuario;
import com.eg.Appclima.security.service.UsuarioService;
import com.eg.Appclima.service.ClimaServiceImpl;
import com.eg.Appclima.service.RegistrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("clima")
@Import({CacheConfig.class, ClimaServiceImpl.class, LimiteReceptorConfig.class, WebMvcConfig.class})
@EnableCaching
@CrossOrigin(origins = "*")
public class ClimaController {

    @Autowired
    private ClimaServiceImpl climaServiceImpl;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RegistrosService registroService;

    @Cacheable(value = "climaCache")
    @GetMapping(value = "/actual/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClimaActualDto getClimaPorCiudad(@RequestParam(required = true) String ciudad, @RequestParam String apiKey, @AuthenticationPrincipal UserDetails userDetails) {

        ClimaActualDto resultado = climaServiceImpl.getClimaActual(ciudad, apiKey);

        if (resultado != null) {
            Usuario usuarioActual = usuarioService.authUsuario();
            String query = "Consulta: " + ciudad;
            String res = "Respuesta: Clima actual. " + resultado.toString();
            registroService.registrarConsulta(usuarioActual, ciudad, res);

            return resultado;
        } else {

            Usuario usuarioActual = usuarioService.authUsuario();
            String query = "Consulta: " + ciudad;
            String res = "Respuesta: No se encontraron datos. ";
            registroService.registrarConsulta(usuarioActual, query, res);

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron datos climáticos sobre la ciudad proporcionada");
        }
    }

    @Cacheable(value = "forecastCache")
    @GetMapping(value = "/forecast/", produces = MediaType.APPLICATION_JSON_VALUE)
    public PronosticoDto getPronosticoPorCiudad(@RequestParam(required = true) String ciudad, @RequestParam String apiKey, @AuthenticationPrincipal UserDetails userDetails) {

        PronosticoDto resultado = climaServiceImpl.getPronosticoClima(ciudad, apiKey);

        if (resultado != null) {
            Usuario usuarioActual = usuarioService.authUsuario();
            String query = "Consulta: " + ciudad;
            String res = "Respuesta: Pronóstico del clima en los siguientes días: " + resultado.getList().get(0).getWeather().get(0).toString();
            registroService.registrarConsulta(usuarioActual, ciudad, res);

            return resultado;
        } else {

            Usuario usuarioActual = usuarioService.authUsuario();
            String query = "Consulta: " + ciudad;
            String res = "Respuesta: No se encontraron datos. ";
            registroService.registrarConsulta(usuarioActual, ciudad, res);

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron datos climáticos sobre la ciudad proporcionada");
        }
    }

    @Cacheable(value = "aireContaminadoCache")
    @GetMapping(value = "/aire/", produces = MediaType.APPLICATION_JSON_VALUE)
    public AireContaminadoDto getAireContaminado(@RequestParam(required = true) String ciudad, @RequestParam String apiKey, @AuthenticationPrincipal UserDetails userDetails) {

        AireContaminadoDto resultado = climaServiceImpl.getCoordenadas(ciudad, apiKey);

        if (resultado != null) {
            Usuario usuarioActual = usuarioService.authUsuario();
            String query = "Consulta: " + ciudad;
            String res = "Respuesta: Contaminación del aire: " + resultado.getList().get(0).getMain().getCalidadAire();
            registroService.registrarConsulta(usuarioActual, ciudad, res);

            return resultado;
        } else {

            Usuario usuarioActual = usuarioService.authUsuario();
            String query = "Consulta: " + ciudad;
            String res = "Respuesta: No se encontraron datos. ";
            registroService.registrarConsulta(usuarioActual, ciudad, res);

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron datos del aire contaminado sobre la ciudad proporcionada");
        }
    }
}
