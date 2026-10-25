package kz.astanait.calculator.client;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import kz.astanait.calculator.Calculator;
import kz.astanait.calculator.strategy.OperationStrategy;


public final class ConsoleSession {

    private final OperationCatalog catalog;
    private final Calculator calculator;
    private final Scanner input;
    private final PrintStream out;
    private final Map<String, Runnable> commands = new LinkedHashMap<>();
    private boolean running = true;

    public ConsoleSession(OperationCatalog catalog, Calculator calculator, InputStream in, PrintStream out) {
        this.catalog = catalog;
        this.calculator = calculator;
        this.input = new Scanner(in);
        this.out = out;
        commands.put("help", this::printHelp);
        commands.put("ops", this::printOperations);
        commands.put("exit", () -> running = false);
    }

    public void run() {
        out.println("Strategy Calculator. Type an expression like '12 * 4', or 'help'.");
        while (running) {
            out.print("> ");
            out.flush();
            if (!input.hasNextLine()) {
                break;
            }
            handle(input.nextLine().trim());
        }
        out.println("Goodbye!");
    }

    private void handle(String line) {
        if (line.isEmpty()) {
            return;
        }
        commands.getOrDefault(line.toLowerCase(), () -> evaluate(line)).run();
    }

    private void evaluate(String line) {
        String[] tokens = line.split("\\s+");
        if (tokens.length != 3) {
            out.println("Expected: <number> <operator> <number>. Type 'help'.");
            return;
        }
        try {
            double left = Double.parseDouble(tokens[0]);
            double right = Double.parseDouble(tokens[2]);
            catalog.find(tokens[1]).ifPresentOrElse(
                    strategy -> compute(strategy, tokens[1], left, right),
                    () -> out.println("Unknown operator '" + tokens[1] + "'. Type 'ops' to list them."));
        } catch (NumberFormatException e) {
            out.println("Operands must be numbers. Type 'help'.");
        }
    }

    private void compute(OperationStrategy strategy, String symbol, double left, double right) {
        calculator.setStrategy(strategy);                       // runtime switch
        try {
            double result = calculator.calculate(left, right);  // delegation
            out.printf("[%s] %s %s %s = %s%n", strategy.getClass().getSimpleName(),
                    ResultFormatter.format(left), symbol, ResultFormatter.format(right),
                    ResultFormatter.format(result));
        } catch (ArithmeticException e) {
            out.println("Error: " + e.getMessage());
        }
    }

    private void printOperations() {
        catalog.all().forEach((symbol, strategy) ->
                out.printf("  %s  ->  %s%n", symbol, strategy.getClass().getSimpleName()));
    }

    private void printHelp() {
        out.println("Usage: <number> <operator> <number>   e.g.  7 ^ 2   or   10 / 4");
        out.println("Commands: ops (list operators), help, exit");
    }
}
