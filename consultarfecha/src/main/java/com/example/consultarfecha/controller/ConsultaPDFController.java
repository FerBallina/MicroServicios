package com.example.consultarfecha.controller;

import com.example.consultarfecha.dto.PdfRequestEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;


@RestController
@Slf4j
@RequestMapping("/api/pdf")
public class ConsultaPDFController {

    @Autowired
    private KafkaTemplate<String, PdfRequestEvent> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaListenerEndpointRegistry registry;

    @PostMapping("/generar")
    public ResponseEntity<String> solicitarPdf(@RequestParam("anio") int anio, 
                                                @RequestParam("usuarioId") Long usuarioId) {
        log.info("-> Generando solicitud de PDF...");
        String requestId = UUID.randomUUID().toString();
        PdfRequestEvent evento = new PdfRequestEvent(requestId, usuarioId, anio);

        // Envía el mensaje al tópico "solicitudes-pdf"
        kafkaTemplate.send("solicitudes-pdf", evento);
        
        // Responde de inmediato. La web usará este ID para revisar el estado.
        //return ResponseEntity.accepted().body(requestId);
        return ResponseEntity.ok("Solicitud enviada y en proceso");
    }

    @GetMapping("/kafka/arrancar")
    public ResponseEntity<String> arrancarListener() {
        // Obtenemos el contenedor por su ID asignado en la anotación y lo encendemos
        MessageListenerContainer container = registry.getListenerContainer("listenerDinamico");
        log.info("-> Iniciando Kafka...");
        if (container != null && !container.isRunning()) {
            container.start(); // <-- Aquí arranca físicamente el consumidor de Kafka
            return ResponseEntity.ok("Consumidor Kafka iniciado correctamente.");
        }
        return ResponseEntity.ok("El Consumidor Kafka ya estaba corriendo o no existe.");
    }

    @GetMapping("/kafka/detener")
    public ResponseEntity<String> detenerListener() {
        MessageListenerContainer container = registry.getListenerContainer("listenerDinamico");
        log.info("-> Deteniendo Kafka...");
        if (container != null && container.isRunning()) {            
            container.stop(); // <-- Detiene el consumo en caliente
            return ResponseEntity.ok("Listener detenido.");
        }
        return ResponseEntity.ok("Consumidor Kafka ya estaba apagado.");
    }
}
