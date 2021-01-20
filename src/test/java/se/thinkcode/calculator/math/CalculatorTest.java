package se.thinkcode.calculator.math;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    private Calculator calculator = new Calculator();

    @Test
    public void should_add_2_and_4_and_get_6() {
        Numeric sum = calculator.add(Numeric.of(2), Numeric.of(4));
        assertEquals(Numeric.of(6), sum);
    }
}
