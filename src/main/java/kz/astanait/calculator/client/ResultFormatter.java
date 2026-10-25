package kz.astanait.calculator.client;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class ResultFormatter {

    private static final int MAX_DECIMAL_PLACES = 10;

    private ResultFormatter() {
    }

    public static String format(double value) {
        if (!Double.isFinite(value)) {
            return String.valueOf(value);
        }
        return BigDecimal.valueOf(value)
                .setScale(MAX_DECIMAL_PLACES, RoundingMode.HALF_UP)
                .stripTrailingZeros()
                .toPlainString();
    }
}
