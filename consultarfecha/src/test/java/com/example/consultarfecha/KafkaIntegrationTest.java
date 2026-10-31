package com.example.consultarfecha;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.consultarfecha.component.ConsultaPDFComponent;
import com.example.consultarfecha.dto.PdfRequestEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes  = ConsultarFechaApplication.class,
    properties = {
        "spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer",
        "spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.JsonDeserializer",
        "spring.kafka.consumer.properties.spring.json.trusted.packages=*",
        "spring.kafka.listener.auto-startup=true"
    }
)
@AutoConfigureMockMvc // Permite simular las peticiones HTTP al Controller
@DirtiesContext // Limpia el contexto de Spring después del test
@EmbeddedKafka(
    partitions = 1, 
    brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" },
    topics = { "solicitudes-pdf" }
)
public class KafkaIntegrationTest {

 @Autowired
    private MockMvc mockMvc; // Simulador HTTP

    @Autowired
    private ConsultaPDFComponent consumerService; // Tu consumidor real

    @Autowired
    private ObjectMapper objectMapper; // Para convertir tu objeto a JSON string

    @BeforeEach
    void setUp() {
        consumerService.resetLatch();
    }

    @Test
    void probarEnvioYRecepcionDeMensaje() throws Exception {

        System.out.println("-> Generando mensaje de prueba para Kafka...");
        // 1. Definir el mensaje de prueba
        PdfRequestEvent eventoMock = new PdfRequestEvent();
        eventoMock.setAnio(2026);
        eventoMock.setUsuarioId(12345L);
        // Agrega aquí el resto de los campos de tu objeto...

        // Convertimos el objeto Java a un String JSON
        String jsonPayload = objectMapper.writeValueAsString(eventoMock);
        
        // como por defecto tengo apagado kafka necesito encenderlo primero
        

        // 2. Simular la petición HTTP POST al endpoint del Controller
        mockMvc.perform(MockMvcRequestBuilders.post("/api/pdf/generar")
                .param("anio", String.valueOf(2026))
                .param("usuarioId", String.valueOf(12345L))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().isOk()); // Verifica que el controller responda HTTP 200

        // 3. El test ESPERA un máximo de 5 segundos a que Kafka distribuya el mensaje y el Listener lo procese
        boolean mensajeProcesado = consumerService.getLatch().await(5, TimeUnit.SECONDS);

        // 4. Verificaciones finales
        assertTrue(mensajeProcesado, "El flujo falló: El Controller no envió el mensaje o el Consumidor no lo recibió a tiempo");
    }
}
