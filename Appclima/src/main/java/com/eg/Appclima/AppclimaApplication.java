package com.eg.Appclima;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppclimaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppclimaApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de datos meteorologicos.")
                        .description("Comunicación con API externa OpenWeatherMap.")
                        .version("0.0.1")
                        .contact(new Contact()
                                .name("Erick Galdámez")
                                .email("erick777gal@gmail.com")
                                .url("https://github.com/ErickAlas07"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

}
