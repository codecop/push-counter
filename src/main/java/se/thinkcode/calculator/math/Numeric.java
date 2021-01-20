package se.thinkcode.calculator.math;

/**
 * A numeric input or output of a calculation.
 */
public final class Numeric {
    private final int value;

    public static Numeric of(int number) {
        return new Numeric(number);
    }

    private Numeric(int value) {
        this.value = value;
    }

    /* package */ int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Numeric)) {
            return false;
        }
        Numeric that = (Numeric) o;
        return this.value == that.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
