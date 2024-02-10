package com.eg.Appclima.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AireContaminado implements Serializable {

    private List<DatosAire> list;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor

    public static class DatosAire implements Serializable {

        @JsonProperty("main")
        private Main main;

        @JsonProperty("components")
        private Component components;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Main implements Serializable {

        @JsonProperty("aqi")
        private int calidadAire;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Component implements Serializable {

        @JsonProperty("co")
        private double monoCarbono;

        @JsonProperty("no")
        private double oxidoNitro;

        @JsonProperty("no2")
        private double dioxidoNitro;

        @JsonProperty("o3")
        private double ozono;

        @JsonProperty("so2")
        private double dioxidoAzufre;

        @JsonProperty("pm2_5")
        private double materiaParticulada2_5;

        @JsonProperty("pm10")
        private double materiaParticulada10;

        @JsonProperty("nh3")
        private double amoniaco;

    }
}
