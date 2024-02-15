package com.eg.Appclima.controller;

import com.eg.Appclima.config.*;
import com.eg.Appclima.dto.AireContaminadoDto;
import com.eg.Appclima.dto.ClimaActualDto;
import com.eg.Appclima.dto.PronosticoDto;
import com.eg.Appclima.entity.Registros;
import com.eg.Appclima.security.entity.Usuario;
import com.eg.Appclima.security.service.UsuarioService;
import com.eg.Appclima.service.ClimaServiceImpl;
import com.eg.Appclima.service.RegistrosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @Operation(summary = "Obtener el clima actual de una ciudad proporcionada. Datos necesarios: ciudad y api key.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Éxito en la operación.",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClimaActualDto.class))}),
        @ApiResponse(responseCode = "404", description = "No se encontraron datos para la ciudad.",
                content = @Content),
        @ApiResponse(responseCode = "403", description = "Usuario sin autorización.",
                content = @Content),
        @ApiResponse(responseCode = "403", description = "Número de peticiones excedidos.",
                content = @Content),
        @ApiResponse(responseCode = "500", description = "Error interno.",
                content = @Content)})
//    @Cacheable(value = "climaCache")
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

    @Operation(summary = "Obtener el pronóstico del clima de los siguientes 5 días de una ciudad proporcionada. Datos necesarios: ciudad y api key.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Éxito en la operación.",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PronosticoDto.class))}),
        @ApiResponse(responseCode = "404", description = "No se encontraron datos de pronóstico para la ciudad.",
                content = @Content),
        @ApiResponse(responseCode = "403", description = "Usuario sin autorización.",
                content = @Content),
        @ApiResponse(responseCode = "429", description = "Número de peticiones excedidos.",
                content = @Content),
        @ApiResponse(responseCode = "500", description = "Error interno.",
                content = @Content)})
    //@Cacheable(value = "forecastCache")
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

    @Operation(summary = "Obtener la contaminación del aire de una ciudad proporcionada. Datos necesarios: ciudad y api key.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Éxito en la operación.",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PronosticoDto.class))}),
        @ApiResponse(responseCode = "404", description = "No se encontraron datos de aire para la ciudad proporcionada.",
                content = @Content),
        @ApiResponse(responseCode = "403", description = "Usuario sin autorización.",
                content = @Content),
        @ApiResponse(responseCode = "429", description = "Número de peticiones excedidos.",
                content = @Content),
        @ApiResponse(responseCode = "500", description = "Error interno.",
                content = @Content)})
    //@Cacheable(value = "aireContaminadoCache")
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
