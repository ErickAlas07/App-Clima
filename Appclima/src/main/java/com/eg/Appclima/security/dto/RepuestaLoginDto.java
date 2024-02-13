package com.eg.Appclima.security.dto;

import lombok.*;

@Getter
@Setter
public class RepuestaLoginDto {
    private String token;
    private long expirado;
}
