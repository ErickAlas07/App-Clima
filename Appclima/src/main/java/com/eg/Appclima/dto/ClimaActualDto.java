package com.eg.Appclima.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.*;

@Data  //Anotación que agrupa las características de @ToString, @EqualsAndHashCode, @Getter/@Setter y @RequiredArgsConstructor.
@NoArgsConstructor
@AllArgsConstructor
public class ClimaActualDto implements Serializable {

    @JsonProperty("name")
    private String ciudad;

    @JsonProperty("main")
    private Main main;

    @JsonProperty("wind")
    private Wind viento;

    @JsonProperty("weather")
    private List<Clima> weather;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Main {

        @JsonProperty("temp")
        private double temp;

        @JsonProperty("pressure")
        private int pressure;

        @JsonProperty("humidity")
        private int humidity;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Wind {

        @JsonProperty("speed")
        private double speed;

        @JsonProperty("deg")
        private int deg;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Clima {

        private String main;
        private String description;
    }
}
