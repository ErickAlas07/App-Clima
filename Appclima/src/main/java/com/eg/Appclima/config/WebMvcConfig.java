package com.eg.Appclima.config;

import com.eg.Appclima.interceptor.LimiteReceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final LimiteReceptor limiteSolicitudes;

    public WebMvcConfig(LimiteReceptor limiteSolicitudes) {
        this.limiteSolicitudes = limiteSolicitudes;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(limiteSolicitudes).addPathPatterns("/clima/**");
    }
}
