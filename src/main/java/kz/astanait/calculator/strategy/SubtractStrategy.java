package kz.astanait.calculator.strategy;

/** Concrete Strategy: subtraction ({@code minuend - subtrahend}). */
public final class SubtractStrategy implements OperationStrategy {

    @Override
    public double execute(double minuend, double subtrahend) {
        return minuend - subtrahend;
    }
}
