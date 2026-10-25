package kz.astanait.calculator.strategy;

@FunctionalInterface
public interface OperationStrategy {

    /**
     * Applies the operation to the two operands.
     *
     * @throws ArithmeticException if the operation is undefined for the given operands
     */
    double execute(double left, double right);
}
