package com.book.bookmanagment.config;  // El paquete donde colocas tu clase de configuración

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  // Anotación que indica que esta clase contiene configuraciones de Spring
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Permitir CORS en todos los endpoints de la API
                .allowedOrigins("http://localhost:5173")  // Permitir solicitudes desde el origen del front-end
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Métodos HTTP permitidos
                .allowedHeaders("*")  // Permitir todos los encabezados
                .allowCredentials(true);  // Permitir uso de credenciales (autenticación)
    }
}
