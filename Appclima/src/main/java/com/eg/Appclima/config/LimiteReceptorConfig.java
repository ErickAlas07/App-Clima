package com.eg.Appclima.config;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.*;

@Configuration
@EnableScheduling
public class LimiteReceptorConfig {

    @Bean
    @Scope("singleton")
    public AtomicInteger requestCounter() {
        return new AtomicInteger(0);
    }

    @Scheduled(fixedRate = 3600000)
    public void resetCounter() {
        requestCounter().set(0);
    }
}
