package com.eg.Appclima.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoordenadasDto implements Serializable {

    private String name;
    private LocalNames local_names;
    private double lat;
    private double lon;
    private String country;
    private String state;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LocalNames implements Serializable {

        private String ms;
        private String gu;
        private String is;
    }
}
