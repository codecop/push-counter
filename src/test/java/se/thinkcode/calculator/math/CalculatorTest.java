package se.thinkcode.calculator.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CalculatorTest {

    private Calculator calculator = new Calculator();

    @Test
    void should_add_2_and_4_and_get_6() {
        Numeric sum = calculator.add(Numeric.of(2), Numeric.of(4));
        assertEquals(Numeric.of(6), sum);
    }
}
