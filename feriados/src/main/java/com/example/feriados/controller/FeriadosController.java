package com.example.feriados.controller;

import com.example.feriados.dto.FeriadoRequest;
import com.example.feriados.dto.FeriadoResponse;
import com.example.feriados.service.FeriadosService;
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
@CrossOrigin(origins = "http://localhost:8081")
public class FeriadosController {

    private final FeriadosService feriadosService;

    public FeriadosController(FeriadosService feriadosService) {
        this.feriadosService = feriadosService;
    }

    @PostMapping("/check")
    public FeriadoResponse checkFeriado(@RequestBody FeriadoRequest request) {
        return feriadosService.checkFeriado(request.getFecha());
    }

    @GetMapping("/all")
    public Set<LocalDate> checkFeriados() {
        return feriadosService.checkFeriados();
    }
}
