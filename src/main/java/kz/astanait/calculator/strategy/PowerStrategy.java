package kz.astanait.calculator.strategy;

public final class PowerStrategy implements OperationStrategy {

    @Override
    public double execute(double base, double exponent) {
        double result = Math.pow(base, exponent);
        if (!Double.isFinite(result)) {
            throw new ArithmeticException("Result is undefined or out of range");
        }
        return result;
    }
}
