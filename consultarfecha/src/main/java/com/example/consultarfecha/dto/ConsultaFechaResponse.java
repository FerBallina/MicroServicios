package com.example.consultarfecha.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class ConsultaFechaResponse {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;
    private int cantidadDias;
    private boolean feriado;

    public ConsultaFechaResponse() {
    }

    public ConsultaFechaResponse(LocalDate fecha, int cantidadDias, boolean feriado) {
        this.fecha = fecha;
        this.cantidadDias = cantidadDias;
        this.feriado = feriado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getCantidadDias() {
        return cantidadDias;
    }

    public void setCantidadDias(int cantidadDias) {
        this.cantidadDias = cantidadDias;
    }

    public boolean isFeriado() {
        return feriado;
    }

    public void setFeriado(boolean feriado) {
        this.feriado = feriado;
    }
}
