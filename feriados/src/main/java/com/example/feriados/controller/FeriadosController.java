package com.example.feriados.controller;

import com.example.feriados.dto.FeriadoRequest;
import com.example.feriados.dto.FeriadoResponse;
import com.example.feriados.service.FeriadosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Set;
import java.time.LocalDate;

@RestController
@RequestMapping("/feriados")
@Tag(name = "Servicio de Fecha", description = "consulta fechas de feriados")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FeriadosController {

    private final FeriadosService feriadosService;

    public FeriadosController(FeriadosService feriadosService) {
        this.feriadosService = feriadosService;
    }

    @PostMapping("/check")
    @Operation(summary = "Consulta una fecha particular", description = "consulta una fecha y la cantidad de dias para ver si es feriado.")
    public FeriadoResponse checkFeriado(@RequestBody FeriadoRequest request) {
        return feriadosService.checkFeriado(request.getFecha());
    }

    @GetMapping("/all")
    public Set<LocalDate> checkFeriados() {
        return feriadosService.checkFeriados();
    }
}
