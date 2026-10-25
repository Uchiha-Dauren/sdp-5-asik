package kz.astanait.calculator;

import java.util.Objects;

import kz.astanait.calculator.strategy.OperationStrategy;

public final class Calculator {

    private OperationStrategy strategy;

    public Calculator(OperationStrategy strategy) {
        setStrategy(strategy);
    }

    public void setStrategy(OperationStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "strategy must not be null");
    }

    public double calculate(double left, double right) {
        return strategy.execute(left, right);
    }
}
