package kz.astanait.calculator;

import kz.astanait.calculator.strategy.AddStrategy;
import kz.astanait.calculator.strategy.MultiplyStrategy;
import kz.astanait.calculator.strategy.OperationStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorTest {

    private static final double DELTA = 1e-9;

    @Test
    @DisplayName("Calculator delegates to the strategy it was given")
    void delegatesToStrategy() {
        Calculator calculator = new Calculator(new AddStrategy());

        assertEquals(7, calculator.calculate(3, 4), DELTA);
    }

    @Test
    @DisplayName("Strategy can be switched at runtime on the same Calculator")
    void switchesStrategyAtRuntime() {
        Calculator calculator = new Calculator(new AddStrategy());
        assertEquals(7, calculator.calculate(3, 4), DELTA);

        calculator.setStrategy(new MultiplyStrategy());

        assertEquals(12, calculator.calculate(3, 4), DELTA);
    }

    @Test
    @DisplayName("Calculator works with any OperationStrategy, even one it has never seen")
    void acceptsCustomStrategy() {
        OperationStrategy maximum = Math::max;

        assertEquals(9, new Calculator(maximum).calculate(9, 4), DELTA);
    }

    @Test
    @DisplayName("A null strategy is rejected immediately")
    void rejectsNullStrategy() {
        assertThrows(NullPointerException.class, () -> new Calculator(null));
        Calculator calculator = new Calculator(new AddStrategy());
        assertThrows(NullPointerException.class, () -> calculator.setStrategy(null));
    }
}
