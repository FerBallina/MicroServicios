package com.example.feriados.controller;

import com.example.feriados.dto.FeriadoRequest;
import com.example.feriados.dto.FeriadoResponse;
import com.example.feriados.service.FeriadosService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Feriados")
public class FeriadosController {

    private final FeriadosService feriadosService;

    public FeriadosController(FeriadosService feriadosService) {
        this.feriadosService = feriadosService;
    }

    @PostMapping
    public FeriadoResponse checkFeriado(@RequestBody FeriadoRequest request) {
        return feriadosService.checkFeriado(request.getFecha());
    }
}
