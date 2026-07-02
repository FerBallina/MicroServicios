package E2EferiadosService;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.feriados.controller.FeriadosController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;


public class feriadosServiceSteps {

    //@Autowired
    //private FeriadosService feriadosService;

    @Autowired
    private FeriadosController feriadosController;

    @Autowired
    private ScenarioContext scenarioContext;

    @Autowired
    private MockMvc mockMvc;

    @Given("llamar al endpoint: \\/feriados\\/all")
    public void llamar_al_endpoint_feriados_all() {
        System.out.println("-> llamando al endpoint feriados/all...");
        //result = feriadosService.checkFeriados();
        //assertTrue(result != null && !result.isEmpty(), "El resultado no debe ser nulo ni vacío");
        scenarioContext.resultDates = feriadosController.checkFeriados();
        assertTrue(scenarioContext.resultDates != null && !scenarioContext.resultDates.isEmpty(), "El resultado no debe ser nulo ni vacío");

    }

    @Given("llamar al endpoint: {string}")
    public void usuarioEnPaginaLogin(String endpoint) {
        System.out.println("-> llamando al endpoint especifico: "+endpoint+"...");
        try {
            scenarioContext.response = mockMvc.perform(MockMvcRequestBuilders.get(endpoint)
                        .contentType(MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            System.out.println("ERROR IN MOCKMVc.......................");
            e.printStackTrace();
        }
        assertTrue(scenarioContext.response != null);
        try {
            scenarioContext.response.andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
        } catch (Exception e) {
            System.out.println("ERROR revisando status.......................");
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());

        try {
            String jsonRespuesta = scenarioContext.response.andReturn()
                                   .getResponse()
                                   .getContentAsString();
            scenarioContext.resultDates = mapper.readValue(jsonRespuesta, new TypeReference<Set<LocalDate>>() {});
        } catch (UnsupportedEncodingException e) {
            System.out.println("ERROR wrong encoding.......................");
            e.printStackTrace();
        }catch (JsonProcessingException e) {
            System.out.println("ERROR Json processing.......................");
            e.printStackTrace();
        }
    }

    @Then("obtener un listado con varias fechas")
    public void redirigidoAInicio() {
        System.out.println("-> obteniendo del endpoint listado de feriados completos.");

        assertTrue(scenarioContext.resultDates != null && scenarioContext.resultDates.size() == 3);
    }
    
}
