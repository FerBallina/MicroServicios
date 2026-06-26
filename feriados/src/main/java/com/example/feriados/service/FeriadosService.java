package com.example.feriados.service;

import com.example.feriados.dto.FeriadoResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FeriadosService {

    private final Set<LocalDate> feriados = new HashSet<>();
    private final ObjectMapper objectMapper;

    public FeriadosService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void init() throws IOException {
        var resource = new ClassPathResource("feriados.json");
        List<String> loaded = objectMapper.readValue(resource.getInputStream(), new TypeReference<>() {});
        for (String fecha : loaded) {
            feriados.add(LocalDate.parse(fecha));
        }
    }

    public FeriadoResponse checkFeriado(LocalDate fecha) {
        boolean esFeriado = feriados.contains(fecha);
        return new FeriadoResponse(fecha, esFeriado);
    }
}
