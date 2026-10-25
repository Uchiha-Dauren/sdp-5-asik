package kz.astanait.calculator.strategy;

public final class DivideStrategy implements OperationStrategy {

    @Override
    public double execute(double dividend, double divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("Division by zero is undefined");
        }
        return dividend / divisor;
    }
}
