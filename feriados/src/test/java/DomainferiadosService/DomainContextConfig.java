package DomainferiadosService;

import io.cucumber.spring.CucumberContextConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import com.example.feriados.service.FeriadosService;
import com.fasterxml.jackson.databind.ObjectMapper;

@CucumberContextConfiguration
// 1. Apuntamos a la clase interna estática que crearemos abajo
@ContextConfiguration(classes = DomainContextConfig.ContextConfig.class) // Solo carga esta clase
public class DomainContextConfig {

 // 2. Esta clase interna define el "mini" contexto de Spring
    @Configuration
    public static class ContextConfig {

        // Instancia el servicio y le pasa el ObjectMapper de abajo
        @Bean
        public FeriadosService feriadosService(ObjectMapper objectMapper) {
            return new FeriadosService(objectMapper);
        }

        // Provee el bean faltante que causaba el error
        @Bean
        public ObjectMapper objectMapper() {
            return new ObjectMapper(); 
        }
    }
}
