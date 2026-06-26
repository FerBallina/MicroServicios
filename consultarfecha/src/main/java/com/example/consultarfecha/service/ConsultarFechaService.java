package com.example.consultarfecha.service;

import com.example.consultarfecha.dto.ConsultaFechaResponse;
import com.example.consultarfecha.dto.FeriadoResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class ConsultarFechaService {

    private final RestTemplate restTemplate;

    public ConsultarFechaService() {
        this.restTemplate = new RestTemplate();
    }

    public ConsultaFechaResponse consultar(LocalDate fecha, int cantidadDias) {
        LocalDate fechaResultado = fecha.plusDays(cantidadDias);

        FeriadoResponse feriadoResponse = consultarFeriado(fechaResultado);

        return new ConsultaFechaResponse(fechaResultado, cantidadDias, feriadoResponse.isListado());
    }

    private FeriadoResponse consultarFeriado(LocalDate fecha) {
        String url = "http://localhost:8080/Feriados";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        var body = new java.util.HashMap<String, Object>();
        body.put("fecha", fecha);

        HttpEntity<java.util.Map<String, Object>> request = new HttpEntity<>(body, headers);
        ResponseEntity<FeriadoResponse> response = restTemplate.postForEntity(url, request, FeriadoResponse.class);
        return response.getBody();
    }
}
