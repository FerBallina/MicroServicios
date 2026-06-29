package com.example.consultarfecha.controller;

import com.example.consultarfecha.dto.ConsultaFechaRequest;
import com.example.consultarfecha.dto.ConsultaFechaResponse;
import com.example.consultarfecha.service.ConsultarFechaService;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/consultarFecha")
@Tag(name = "Consultar Fecha", description = "consulta fechas de feriados")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class ConsultarFechaController {
    private final ConsultarFechaService consultarFechaService;

    public ConsultarFechaController(ConsultarFechaService consultarFechaService) {
        this.consultarFechaService = consultarFechaService;
    }
    
    @PostMapping("/consultarFecha")
    @Operation(summary = "Consulta una fecha particular", description = "consulta una fecha y la cantidad de dias para ver si es feriado.")
    public ConsultaFechaResponse consultarFecha(@RequestBody ConsultaFechaRequest request) {
        return consultarFechaService.consultar(request.getFecha(), request.getCantidadDias());
    }

    @GetMapping("/today")
    public ConsultaFechaResponse consultarFecha() {
        return consultarFechaService.consultarToday();
    }

    @GetMapping("/all")
    public List<String> consultarTodos() {
        return consultarFechaService.consultarTodos();
    }
}
