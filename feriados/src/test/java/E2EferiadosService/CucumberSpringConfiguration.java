package E2EferiadosService;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.example.feriados.FeriadosApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = FeriadosApplication.class, 
    webEnvironment = WebEnvironment.RANDOM_PORT)
public class CucumberSpringConfiguration {
    // Leave this class empty. 
    // Its only job is to spin up the Spring Context for Cucumber.

    //mock classes
}
