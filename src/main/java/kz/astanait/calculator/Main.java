package kz.astanait.calculator;

import java.util.Arrays;

import kz.astanait.calculator.client.ConsoleSession;
import kz.astanait.calculator.client.Demo;
import kz.astanait.calculator.client.OperationCatalog;


public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        OperationCatalog catalog = OperationCatalog.standard();

        if (Arrays.asList(args).contains("--demo")) {
            Demo.run(catalog, System.out);
            return;
        }
        Calculator calculator = new Calculator(catalog.require("+"));
        new ConsoleSession(catalog, calculator, System.in, System.out).run();
    }
}
