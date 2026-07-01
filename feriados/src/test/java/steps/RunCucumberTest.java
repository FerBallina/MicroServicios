package steps;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/E2E") // Ruta en src/test/resources
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "E2EferiadosService") // Paquete de Java
public class RunCucumberTest {
    // Esta clase queda vacía, solo sirve de configuración
}
