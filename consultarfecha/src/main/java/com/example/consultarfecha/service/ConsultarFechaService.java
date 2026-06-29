package com.example.consultarfecha.service;

import com.example.consultarfecha.dto.ConsultaFechaResponse;
import com.example.consultarfecha.dto.FeriadoResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class ConsultarFechaService {

    private final RestTemplate restTemplate;

    public ConsultarFechaService() {
        this.restTemplate = new RestTemplate();
    }

    public ConsultaFechaResponse consultar(LocalDate fecha, int cantidadDias) {
        log.info("Fecha recibida: {}, dias: {}", fecha, cantidadDias);
        System.out.println("Fecha recibida: " + fecha);
        System.out.println("Cantidad de días: " + cantidadDias);
        
        if ( fecha == null){
            return new ConsultaFechaResponse(LocalDate.now() , cantidadDias, false);
        }else{
            LocalDate fechaResultado = fecha.plusDays(cantidadDias);

            FeriadoResponse feriadoResponse = consultarFeriado(fechaResultado);
            return new ConsultaFechaResponse(fechaResultado, cantidadDias, feriadoResponse.isListado());
        }
    }

    public ConsultaFechaResponse consultarToday() {
        log.info("Sin fecha recibida");
    
        return new ConsultaFechaResponse(LocalDate.now() , 0, false);
    }

    public List<String> consultarTodos() {
        log.info("Devolviendo todos los feriados");
    
        return consultarFeriados();
    }

    private FeriadoResponse consultarFeriado(LocalDate fecha) {
        String url = "http://localhost:8082/feriados/check";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        var body = new java.util.HashMap<String, Object>();
        body.put("fecha", fecha);

        HttpEntity<java.util.Map<String, Object>> request = new HttpEntity<>(body, headers);
        ResponseEntity<FeriadoResponse> response = restTemplate.postForEntity(url, request, FeriadoResponse.class);
        return response.getBody();
    }

    @SuppressWarnings("null")
    private List<String> consultarFeriados() {
        String url = "http://localhost:8082/feriados/all";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ParameterizedTypeReference<List<String>> responseType = new ParameterizedTypeReference<List<String>>() {};
        ResponseEntity<List<String>> resp = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
        return resp.getBody();
    }
}
