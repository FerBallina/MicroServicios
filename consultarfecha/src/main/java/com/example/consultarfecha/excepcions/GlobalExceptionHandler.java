package com.example.consultarfecha.excepcions;

//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//import jakarta.servlet.http.HttpServletRequest;
//import java.time.LocalDateTime;
//import java.util.List;

//TODO agregar el controlleraAdvice para manejar las exepciones glopbalmente

@RestControllerAdvice
public class GlobalExceptionHandler {
/*
    // Captura errores de negocio como recurso no encontrado
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> manejarRecursoNoEncontrado(
            Exception ex, HttpServletRequest request) {
        
        ErrorResponse error = new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            request.getRequestURI(),
            null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // Captura fallas de validación en los @Valid DTOs
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> manejarErroresValidacion(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        
        List<ErrorResponse.ValidationError> fallos = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(f -> new ErrorResponse.ValidationError(f.getField(), f.getDefaultMessage()))
            .toList();

        ErrorResponse error = new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            "Error de validación en los datos de entrada",
            request.getRequestURI(),
            fallos
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
        */
}
