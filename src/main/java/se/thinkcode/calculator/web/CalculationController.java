package se.thinkcode.calculator.web;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.thinkcode.calculator.math.Calculator;
import se.thinkcode.calculator.math.Numeric;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

/**
 * Boundary/layer between HTML UI and the calculation domain.
 */
public class CalculationController implements BiFunction<Request, Response, ModelAndView> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Calculator calculator = new Calculator();

    @Override
    public ModelAndView apply(Request request, @SuppressWarnings("unused") Response response) {
        // get parameters
        String httpFirst = request.queryParams("first");
        String httpSecond = request.queryParams("second");
        String httpCommand = request.queryParams("button");
        logger.info(String.format("Received %s %s %s", httpFirst, httpCommand, httpSecond));

        // convert parameters to domain
        Numeric first = parse(httpFirst);
        Numeric second = parse(httpSecond);

        // invoke logic
        final Numeric result;
        switch (httpCommand) {
        case "+":
            result = calculator.add(first, second);
            break;
        default:
            throw new UnsupportedOperationException(httpCommand);
        }

        // convert result back to UI
        String description = String.format("%s %s %s = %s", first, httpCommand, second, result);
        logger.info(description);

        // put template variables
        return render(description);
    }

    private Numeric parse(String param) {
        return Numeric.of(Integer.parseInt(param));
    }

    public ModelAndView render(String description) {
        Map<String, Object> model = new HashMap<>();
        model.put("result", description);
        return new ModelAndView(model, "calculator.mustache");
    }

}