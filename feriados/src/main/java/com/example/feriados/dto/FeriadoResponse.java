package com.example.feriados.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class FeriadoResponse {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;
    private boolean listado;

    public FeriadoResponse() {
    }

    public FeriadoResponse(LocalDate fecha, boolean listado) {
        this.fecha = fecha;
        this.listado = listado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isListado() {
        return listado;
    }

    public void setListado(boolean listado) {
        this.listado = listado;
    }
}
