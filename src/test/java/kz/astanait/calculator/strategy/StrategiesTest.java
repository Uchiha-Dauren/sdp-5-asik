package kz.astanait.calculator.strategy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StrategiesTest {

    private static final double DELTA = 1e-9;

    @Test
    @DisplayName("AddStrategy adds two numbers")
    void addStrategyAdds() {
        assertEquals(7, new AddStrategy().execute(3, 4), DELTA);
        assertEquals(-1, new AddStrategy().execute(2, -3), DELTA);
    }

    @Test
    @DisplayName("SubtractStrategy subtracts the second operand from the first")
    void subtractStrategySubtracts() {
        assertEquals(-1, new SubtractStrategy().execute(3, 4), DELTA);
        assertEquals(5, new SubtractStrategy().execute(2, -3), DELTA);
    }

    @Test
    @DisplayName("MultiplyStrategy multiplies two numbers")
    void multiplyStrategyMultiplies() {
        assertEquals(12, new MultiplyStrategy().execute(3, 4), DELTA);
        assertEquals(0, new MultiplyStrategy().execute(0, 99), DELTA);
    }

    @Test
    @DisplayName("DivideStrategy divides the first operand by the second")
    void divideStrategyDivides() {
        assertEquals(2.5, new DivideStrategy().execute(10, 4), DELTA);
    }

    @Test
    @DisplayName("DivideStrategy rejects division by zero")
    void divideStrategyRejectsZero() {
        assertThrows(ArithmeticException.class, () -> new DivideStrategy().execute(1, 0));
    }

    @Test
    @DisplayName("PowerStrategy raises the base to the exponent")
    void powerStrategyRaises() {
        assertEquals(1024, new PowerStrategy().execute(2, 10), DELTA);
        assertEquals(0.5, new PowerStrategy().execute(2, -1), DELTA);
    }

    @Test
    @DisplayName("PowerStrategy rejects undefined and overflowing results")
    void powerStrategyRejectsInvalidResults() {
        assertThrows(ArithmeticException.class, () -> new PowerStrategy().execute(-8, 0.5));
        assertThrows(ArithmeticException.class, () -> new PowerStrategy().execute(2, 5000));
    }
}
