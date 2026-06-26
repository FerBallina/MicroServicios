package com.example.feriados.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class FeriadoRequest {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
