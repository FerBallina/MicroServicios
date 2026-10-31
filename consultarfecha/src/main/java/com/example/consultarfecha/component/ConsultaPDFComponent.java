package com.example.consultarfecha.component;

import com.example.consultarfecha.dto.PdfRequestEvent;

import java.util.concurrent.CountDownLatch;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;  

@Component
public class ConsultaPDFComponent {
    
    private CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(id = "listenerDinamico", topics = "solicitudes-pdf", groupId = "grupo-procesadores-pdf")// autoStartup = "false"
    public void procesarSolicitudPdf(PdfRequestEvent evento) {
        System.out.println("Iniciando generación de PDF para el año: " + evento.getAnio());
        
        // 1. Aquí buscas los datos en la BD según el año
        // 2. Generas el PDF (usando iText, JasperReports, etc.)
        // 3. Guardas el archivo en disco o nube (ej. /archivos/pdf/ID_UNICO.pdf)
        // 4. Cambias el estado en tu BD a "COMPLETADO" para que la web lo sepa
        
        System.out.println("PDF " + evento.getRequestId() + " generado con éxito.");
        latch.countDown(); 
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public void resetLatch() {
        this.latch = new CountDownLatch(1);
    }
}
