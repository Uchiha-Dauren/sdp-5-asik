# Strategy Calculator

ShP-2216 Software Design Patterns - Assignment: **Strategy** (Option B - Calculator), Java 17.

A `Calculator` (Context) performs an arithmetic operation through an interchangeable
`OperationStrategy`. The strategy can be swapped at runtime, and there is no `if`/`switch`
on the operation type anywhere in the Context.





1. **Create the strategy** (new file, nothing else is modified):

   ```java
   package kz.astanait.calculator.strategy;

   public final class ModuloStrategy implements OperationStrategy {
       @Override
       public double execute(double dividend, double divisor) {
           if (divisor == 0) {
               throw new ArithmeticException("Modulo by zero is undefined");
           }
           return dividend % divisor;
       }
   }
   ```

2. **Register it** in `OperationCatalog.standard()` (one line, in the Client):

   ```java
   catalog.register("%", new ModuloStrategy());
   ```

3. (Optional) add a test in `StrategiesTest`.

`Calculator` and the existing strategies stay untouched (Open/Closed Principle).
Because `OperationStrategy` is a functional interface, a quick strategy can also be a lambda:
`calculator.setStrategy((a, b) -> Math.max(a, b));`

## Documentation

The report (UML diagram, Clean Code justification, Strategy vs. State) is in [`docs/REPORT.md`](docs/REPORT.md).
# sdp-5-asik
