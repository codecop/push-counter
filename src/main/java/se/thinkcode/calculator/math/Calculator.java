package se.thinkcode.calculator.math;

/**
 * Calculator core logic.
 */
public class Calculator {

    public Numeric add(Numeric first, Numeric second) {
        int sum = first.getValue() + second.getValue();
        return Numeric.of(sum);
    }
}
