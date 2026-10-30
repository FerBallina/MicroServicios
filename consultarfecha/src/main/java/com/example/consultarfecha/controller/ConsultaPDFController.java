package com.example.consultarfecha.controller;

import com.example.consultarfecha.dto.PdfRequestEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;


@RestController
@RequestMapping("/api/pdf")
public class ConsultaPDFController {

    @Autowired
    private KafkaTemplate<String, PdfRequestEvent> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/generar")
    public ResponseEntity<String> solicitarPdf(@RequestParam("anio") int anio, 
                                                @RequestParam("usuarioId") Long usuarioId) {
        System.out.println("-> Generando solicitud de PDF...");
        String requestId = UUID.randomUUID().toString();
        PdfRequestEvent evento = new PdfRequestEvent(requestId, usuarioId, anio);

        // Envía el mensaje al tópico "solicitudes-pdf"
        kafkaTemplate.send("solicitudes-pdf", evento);
        
        // Responde de inmediato. La web usará este ID para revisar el estado.
        //return ResponseEntity.accepted().body(requestId);
        return ResponseEntity.ok("Solicitud recibida y en proceso");
    }
}
