package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class feriadosService {

    @Given("llamar al endpoint: \\/feriados\\/all")
    public void llamar_al_endpoint_feriados_all() {
        System.out.println("-> Navegando a la página de login...");
    }

    @Given("llamar al endpoint: {string}")
    public void usuarioEnPaginaLogin(String endpoint) {
        System.out.println("-> Navegando a la página de login...");
    }

    @Then("obtener un listado con varias fechas")
    public void redirigidoAInicio() {
        System.out.println("-> Validando redirección exitosa.");
    }
    
}
