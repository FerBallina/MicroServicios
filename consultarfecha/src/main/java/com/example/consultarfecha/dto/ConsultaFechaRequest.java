package com.example.consultarfecha.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class ConsultaFechaRequest {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;
    private int cantidadDias;

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
}
