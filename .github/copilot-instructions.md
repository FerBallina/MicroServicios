# Directrices de desarrollo para Spring Boot

## Configuración y Estilo de Pruebas
- Utiliza **JUnit 5** como framework de pruebas por defecto (`org.junit.jupiter.api.*`).
- Prohibido usar JUnit 4 u otras librerías de testing obsoletas.
- Todas las clases de prueba deben terminar obligatoriamente con el sufijo `_Test` (ejemplos: `UsuarioController_Test.java`, `ServicioEnvio_Test.java`).

## Convenciones de Spring Boot
- Para pruebas de integración completas, utiliza `@SpringBootTest` junto con `@AutoConfigureMockMvc` si es necesario.
- Para pruebas unitarias de controladores, prefiere `@WebMvcTest` para cargar solo la capa web y acelerar la ejecución.
- Utiliza `@MockBean` de Spring Boot para mockear dependencias dentro del contexto de Spring, o `@Mock` de Mockito si es una prueba unitaria pura.
- Si generas clases de repositorio de datos, utiliza `@DataJpaTest`.

## Nombres de Métodos de Prueba
- Los métodos de prueba deben ser descriptivos y seguir el patrón: `nombreMetodo_escenario_resultadoEsperado` o usar la anotación `@DisplayName` en español.

## Manejo Global de Excepciones
- Utiliza una estrategia centralizada mediante la anotación `@ControllerAdvice` o `@RestControllerAdvice`.
- **Estructura de Error Estándar:** Define siempre un objeto o record de respuesta común (por ejemplo, `ErrorResponse`) que incluya campos clave como: `timestamp`, `status`, `error` (mensaje amigable) y `path`.
- **Validaciones:** Captura de forma explícita la excepción `MethodArgumentNotValidException` para devolver una lista detallada de los campos que fallaron en las validaciones de los DTOs.
- **Excepciones de Negocio/Persistencia:** Crea excepciones personalizadas de tipo `RuntimeException` (como `ResourceNotFoundException`) y mapéalas en el handler con el código de estado HTTP correcto (ej. `404 Not Found`).
- Evita exponer stack traces o excepciones internas del framework (como `DataAccessException` o `SQLException`) directamente hacia el cliente.

## Documentación de la API (Swagger / OpenAPI 3)
- Utiliza la librería **springdoc-openapi-starter-webmvc-ui** para la autogeneración de la documentación.
- **Controladores:** Cada controlador debe estar anotado con `@Tag`, definiendo un nombre claro y una descripción para el grupo de endpoints (ej. `@Tag(name = "Productos", description = "Operaciones sobre el catálogo")`).
- **Endpoints (Métodos):** 
  - Utiliza `@Operation` para describir brevemente qué hace el endpoint (`summary` y `description`).
  - Define las respuestas esperadas usando `@ApiResponse`, detallando los códigos HTTP comunes (ej. 200 OK, 201 Created, 400 Bad Request, 404 Not Found) y enlazándolos al componente de respuesta o error correspondiente.
- **Modelos y DTOs:** Anota las propiedades de los DTOs y Records con `@Schema` para proporcionar descripciones legibles, valores de ejemplo (`example = "19.99"`) y marcar si el campo es obligatorio.

## Logs (Trazabilidad y SLF4J)
- Utiliza la anotación `@Slf4j` de **Lombok** para inyectar el logger automáticamente en cualquier clase. No crees instancias manuales de `LoggerFactory`.
- **Niveles de Log:**
  - `info`: Registra eventos significativos del ciclo de vida de la aplicación o del negocio (ej. inicio de un proceso, transacciones completadas).
  - `warn`: Registra situaciones inesperadas que no detienen la aplicación pero requieren atención (ej. reintentos, caídas secundarias de red).
  - `error`: Registra fallos críticos del sistema, excepciones atrapadas en el `GlobalExceptionHandler` o pérdida de datos. Incluye siempre la excepción en los argumentos del log para capturar el stack trace (`log.error("Mensaje", ex);`).
  - `debug`: Registra detalles técnicos útiles solo para desarrollo (ej. payloads de DTOs mapeados).
- **Formato:** Usa marcadores de posición `{}` para la interpolación de strings. Evita la concatenación de cadenas con el operador `+`.

## Métricas y Monitoreo (Actuator & Micrometer)
- Expón métricas estándar a través de **Spring Boot Actuator** y utiliza la librería **Micrometer** para recolectar datos estructurados.
- **Métricas personalizadas:** Cuando se requiera medir lógica de negocio (ej. número de compras procesadas, tiempo de respuesta de servicios externos), inyecta `MeterRegistry` de Micrometer.
- Prefiere el uso de **Counters** (para contar eventos acumulativos) y **Timers** (para medir duraciones y latencias).
- Agrega siempre **Tags** clave a tus métricas (ej. `vendedor`, `tipo_pago`) para facilitar el filtrado posterior en dashboards como Prometheus/Grafana.
