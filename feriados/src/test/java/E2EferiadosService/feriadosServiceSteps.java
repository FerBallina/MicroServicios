package E2EferiadosService;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.feriados.controller.FeriadosController;
import com.example.feriados.service.FeriadosService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class feriadosServiceSteps {

    Set<LocalDate> result;

    @Autowired
    private FeriadosService feriadosService;

    @Autowired
    private FeriadosController feriadosController;

    @Given("llamar al endpoint: \\/feriados\\/all")
    public void llamar_al_endpoint_feriados_all() {
        System.out.println("-> llamando al endpoint feriados/all...");
        result = feriadosService.checkFeriados();
        assertTrue(result != null && !result.isEmpty(), "El resultado no debe ser nulo ni vacío");
        result = feriadosController.checkFeriados();
        assertTrue(result != null && !result.isEmpty(), "El resultado no debe ser nulo ni vacío");
    }

    @Given("llamar al endpoint: {string}")
    public void usuarioEnPaginaLogin(String endpoint) {
        System.out.println("-> llamando al endpoint especifico: "+endpoint+"...");
    }

    @Then("obtener un listado con varias fechas")
    public void redirigidoAInicio() {
        System.out.println("-> obteniendo listado de feriados completos.");
    }
    
}
