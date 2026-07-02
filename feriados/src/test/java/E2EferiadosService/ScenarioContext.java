package E2EferiadosService;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.ResultActions;

@Component
public class ScenarioContext {

    public Set<LocalDate> resultDates;

    public ResultActions response;

}
