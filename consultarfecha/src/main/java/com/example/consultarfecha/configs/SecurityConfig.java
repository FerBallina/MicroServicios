package com.example.consultarfecha.configs;
//TODO verificar el usa de security, por ahora se deshabilita para poder usar swagger y probar el microservicio
/* 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 1. Disable CSRF (Common for Stateless REST APIs)
            .csrf(csrf -> csrf.disable()) 
            
            // 2. Configure Endpoint Authorization Rules
            .authorizeHttpRequests(auth -> auth
                // Whitelist ALL OpenAPI 3 and Swagger paths
                .requestMatchers(
                    "/v3/api-docs",
                    "/v3/api-docs/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/swagger-resources/**",
                    "/webjars/**"
                ).permitAll() 
                
                // Protect everything else
                .anyRequest().permitAll()//TODO verificar autenticacion
            )
            
            // 3. Make session management stateless (JWT / Token-based)
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            
            // 4. Enable standard HTTP Basic login if needed (or JWT filter)
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
*/