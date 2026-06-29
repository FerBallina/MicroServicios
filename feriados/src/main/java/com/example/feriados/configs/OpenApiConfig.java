package com.example.feriados.configs;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        // Al usar "/" como URL, Swagger usará el mismo dominio de Codespaces desde el que abriste la página
        Server relativeServer = new Server().url("/").description("Servidor Codespaces (Relativo)");
        
        return new OpenAPI().servers(List.of(relativeServer));
    }
}
