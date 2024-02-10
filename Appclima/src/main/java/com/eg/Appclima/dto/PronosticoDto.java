package com.eg.Appclima.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PronosticoDto implements Serializable {

    private String cod;
    private int message;
    private int cnt;
    private List<Datos> list;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Datos implements Serializable {

        private long dt;
        private Main main;
        private List<Weather> weather;
        private Clouds clouds;
        private Wind wind;
        private int visibility;
        private int pop;
        @JsonProperty("dt_txt")
        private String dtTxt;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Main implements Serializable {

            private double temp;

            private double feelsLike;

            @JsonProperty("temp_min")
            private double tempMin;

            @JsonProperty("temp_max")
            private double tempMax;

            private int pressure;

            @JsonProperty("sea_level")
            private int seaLevel;

            @JsonProperty("grnd_level")
            private int grndLevel;

            private int humidity;

            @JsonProperty("temp_kf")
            private double tempKf;
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Weather implements Serializable {

            private int id;
            private String main;
            private String description;
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Clouds implements Serializable {

            private int all;
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Wind implements Serializable {

            private double speed;
            private int deg;
            private double gust;
        }
    }
}
