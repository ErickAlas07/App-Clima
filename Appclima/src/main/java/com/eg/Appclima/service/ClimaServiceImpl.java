package com.eg.Appclima.service;

import com.eg.Appclima.dto.AireContaminadoDto;
import com.eg.Appclima.dto.ClimaActualDto;
import com.eg.Appclima.dto.CoordenadasDto;
import com.eg.Appclima.dto.PronosticoDto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClimaServiceImpl implements ClimaService {

    private static final String WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/weather";
    private static final String FORECAST_API_URL = "http://api.openweathermap.org/data/2.5/forecast";
    private static final String AIR_POLLUTION_API_URL = "http://api.openweathermap.org/data/2.5/air_pollution";
    private static final String GEOCODING_API_URL = "http://api.openweathermap.org/geo/1.0/direct";

    private final RestTemplate restTemplate;

    @Autowired
    public ClimaServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ClimaActualDto getClimaActual(String ciudad, String apiKey) {
        return this.restTemplate.getForObject(urlClima(ciudad, apiKey), ClimaActualDto.class);
    }

    @Override
    public PronosticoDto getPronosticoClima(String ciudad, String apiKey) {
        PronosticoDto pronosticoDto = this.restTemplate.getForObject(urlPronostico(ciudad, apiKey), PronosticoDto.class);

        if (pronosticoDto != null && !pronosticoDto.getList().isEmpty()) {
            List<PronosticoDto.Datos> entries = new ArrayList<>();
            Set<LocalDate> uniqueDates = new HashSet<>();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            for (PronosticoDto.Datos datos : pronosticoDto.getList()) {
                LocalDateTime dateTime = LocalDateTime.parse(datos.getDtTxt(), formatter);
                LocalDate date = dateTime.toLocalDate();

                if (uniqueDates.add(date)) {
                    entries.add(datos);
                }
            }

            pronosticoDto.setList(entries);
            return pronosticoDto;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encuentran datos de la ciudad.");
        }
    }

    @Override
    public AireContaminadoDto getAireContaminado(double lat, double lon, String apiKey) {
        return this.restTemplate.getForObject(urlAireContaminado(lat, lon, apiKey), AireContaminadoDto.class);
    }

    public AireContaminadoDto getCoordsByCityName(String ciudad, String apiKey) {

        CoordenadasDto[] response = this.restTemplate.getForObject(urlGeo(ciudad, apiKey), CoordenadasDto[].class);

        if (response != null && response.length > 0 && response[0] != null) {

            double lat = response[0].getLat();
            double lon = response[0].getLon();

            AireContaminadoDto aireDto = getAireContaminado(lat, lon, apiKey);

            return aireDto;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontaron coordenadas.");
        }
    }

    private String urlClima(String ciudad, String apiKey) {
        if (!apiKey.isEmpty() && apiKey != null) {
            return String.format(WEATHER_API_URL.concat("?q=%s").concat("&limit=1").concat("&appid=%s").concat("&units=metric"), ciudad, apiKey);
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Clave API incorrecta.");
        }
    }

    private String urlPronostico(String ciudad, String apiKey) {
        if (!apiKey.isEmpty() && apiKey != null) {
            return String.format(FORECAST_API_URL.concat("?q=%s").concat("&appid=%s").concat("&units=metric"), ciudad, apiKey);
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Clave API incorrecta.");
        }
    }

    private String urlAireContaminado(double lat, double lon, String apiKey) {
        if (!apiKey.isEmpty() && apiKey != null) {
            return String.format(AIR_POLLUTION_API_URL.concat("?lat=%s").concat("&lon=%s").concat("&appid=%s"), lat, lon, apiKey);
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Clave API incorrecta.");
        }
    }

    private String urlGeo(String ciudad, String apiKey) {
        if (!apiKey.isEmpty() && apiKey != null) {
            return String.format(GEOCODING_API_URL.concat("?q=%s").concat("&appid=%s"), ciudad, apiKey);
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid Api Key.");
        }

    }

}
