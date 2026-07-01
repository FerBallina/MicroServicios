package DomainferiadosService;

import io.cucumber.java.en.Given;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.feriados.service.FeriadosService;


public class DomainSteps {

    @Autowired
    private FeriadosService feriadosService; // Inyectado rápidamente

    @Given("Probando el metodo all de FeriadosService")
    public void checkFeriadosAll() {
        System.out.println("-> Probando metodo /all...");
        Set<LocalDate> result = feriadosService.checkFeriados();
        assertTrue(result != null && !result.isEmpty(), "El resultado no debe ser nulo ni vacío");
        assertTrue(result.size() == 3, "Tengo 3 feriados por ahora");
        System.out.println(result);
    }
}
