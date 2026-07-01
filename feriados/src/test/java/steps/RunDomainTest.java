package steps;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import io.cucumber.junit.platform.engine.Constants;

@Suite
@SelectClasspathResource("features/DomainServices") // Ruta a tus archivos .feature
@ConfigurationParameter(
    key = Constants.GLUE_PROPERTY_NAME, 
    value = "DomainferiadosService" // <-- IMPORTANTE: Solo escanea el paquete ligero
)
public class RunDomainTest {
}
