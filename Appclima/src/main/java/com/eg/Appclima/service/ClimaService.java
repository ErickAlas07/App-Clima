package com.eg.Appclima.service;

import com.eg.Appclima.dto.*;


public interface ClimaService {
    
    ClimaActualDto getClimaActual(String ciudad, String apiKey);
    
    PronosticoDto getPronosticoClima(String ciudad, String apiKey);
    
    AireContaminadoDto getAireContaminado(double lat, double lon, String apiKey);
}
