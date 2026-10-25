package kz.astanait.calculator.strategy;

public final class MultiplyStrategy implements OperationStrategy {

    @Override
    public double execute(double multiplicand, double multiplier) {
        return multiplicand * multiplier;
    }
}
