package kz.astanait.calculator.client;

import kz.astanait.calculator.Calculator;
import kz.astanait.calculator.strategy.AddStrategy;
import kz.astanait.calculator.strategy.DivideStrategy;
import kz.astanait.calculator.strategy.MultiplyStrategy;
import kz.astanait.calculator.strategy.PowerStrategy;
import kz.astanait.calculator.strategy.SubtractStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OperationCatalogTest {

    private static final double DELTA = 1e-9;

    @Test
    @DisplayName("Standard catalog maps each symbol to the right strategy")
    void standardCatalogMapsSymbols() {
        OperationCatalog catalog = OperationCatalog.standard();

        assertTrue(catalog.require("+") instanceof AddStrategy);
        assertTrue(catalog.require("-") instanceof SubtractStrategy);
        assertTrue(catalog.require("*") instanceof MultiplyStrategy);
        assertTrue(catalog.require("/") instanceof DivideStrategy);
        assertTrue(catalog.require("^") instanceof PowerStrategy);
    }

    @Test
    @DisplayName("Unknown symbol is reported, not silently ignored")
    void unknownSymbol() {
        OperationCatalog catalog = OperationCatalog.standard();

        assertFalse(catalog.find("%").isPresent());
        assertThrows(IllegalArgumentException.class, () -> catalog.require("%"));
    }

    @Test
    @DisplayName("A new strategy can be registered without modifying Calculator or existing strategies")
    void newStrategyCanBeRegistered() {
        OperationCatalog catalog = OperationCatalog.standard();
        catalog.register("%", (left, right) -> left % right);

        Calculator calculator = new Calculator(catalog.require("%"));

        assertEquals(1, calculator.calculate(7, 2), DELTA);
    }

    @Test
    @DisplayName("ResultFormatter hides floating-point noise and trailing zeros")
    void formatterIsReadable() {
        assertEquals("16", ResultFormatter.format(16.0));
        assertEquals("0.3", ResultFormatter.format(0.1 + 0.2));
        assertEquals("-2.5", ResultFormatter.format(-2.5));
    }
}
