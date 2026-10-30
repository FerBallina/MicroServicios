    package com.example.consultarfecha.dto;

public class PdfRequestEvent {
    private String requestId;
    private Long usuarioId;
    private int anio;

    public PdfRequestEvent(String requestId, Long usuarioId, int anio) {
        this.requestId = requestId;
        this.usuarioId = usuarioId;
        this.anio = anio;
    }

    public PdfRequestEvent() {
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getAnio() {
        return anio;    
    }

    public String getRequestId() {
        return requestId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }
}
