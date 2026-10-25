package kz.astanait.calculator.client;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import kz.astanait.calculator.strategy.AddStrategy;
import kz.astanait.calculator.strategy.DivideStrategy;
import kz.astanait.calculator.strategy.MultiplyStrategy;
import kz.astanait.calculator.strategy.OperationStrategy;
import kz.astanait.calculator.strategy.PowerStrategy;
import kz.astanait.calculator.strategy.SubtractStrategy;


public final class OperationCatalog {

    private final Map<String, OperationStrategy> strategiesBySymbol = new LinkedHashMap<>();

    /** The catalog used by the application. */
    public static OperationCatalog standard() {
        OperationCatalog catalog = new OperationCatalog();
        catalog.register("+", new AddStrategy());
        catalog.register("-", new SubtractStrategy());
        catalog.register("*", new MultiplyStrategy());
        catalog.register("/", new DivideStrategy());
        catalog.register("^", new PowerStrategy());
        return catalog;
    }

    public void register(String symbol, OperationStrategy strategy) {
        strategiesBySymbol.put(symbol, strategy);
    }

    public Optional<OperationStrategy> find(String symbol) {
        return Optional.ofNullable(strategiesBySymbol.get(symbol));
    }

    public OperationStrategy require(String symbol) {
        return find(symbol).orElseThrow(
                () -> new IllegalArgumentException("Unknown operator: " + symbol));
    }

    public Map<String, OperationStrategy> all() {
        return Collections.unmodifiableMap(strategiesBySymbol);
    }
}
