package kz.astanait.calculator.client;

import java.io.PrintStream;

import kz.astanait.calculator.Calculator;
import kz.astanait.calculator.strategy.OperationStrategy;

public final class Demo {

    private static final double LEFT = 12;
    private static final double RIGHT = 4;

    private Demo() {
    }

    public static void run(OperationCatalog catalog, PrintStream out) {
        Calculator calculator = new Calculator(catalog.require("+"));

        out.println("=== 1. One Calculator, strategies switched at runtime ===");
        catalog.all().forEach((symbol, strategy) -> {
            calculator.setStrategy(strategy);
            print(out, strategy, symbol, calculator);
        });

        out.println();
        out.println("=== 2. A strategy can reject invalid input ===");
        calculator.setStrategy(catalog.require("/"));
        try {
            calculator.calculate(LEFT, 0);
        } catch (ArithmeticException e) {
            out.println("[DivideStrategy] 12 / 0 -> Error: " + e.getMessage());
        }

        out.println();
        out.println("=== 3. New strategy without touching Calculator or existing strategies ===");
        OperationStrategy maximum = Math::max;   // OperationStrategy is a functional interface
        calculator.setStrategy(maximum);
        out.println("[max (lambda)] max(12, 4) = " + ResultFormatter.format(calculator.calculate(LEFT, RIGHT)));
    }

    private static void print(PrintStream out, OperationStrategy strategy, String symbol, Calculator calculator) {
        out.printf("%-19s %s %s %s = %s%n", "[" + strategy.getClass().getSimpleName() + "]",
                ResultFormatter.format(LEFT), symbol, ResultFormatter.format(RIGHT),
                ResultFormatter.format(calculator.calculate(LEFT, RIGHT)));
    }
}
