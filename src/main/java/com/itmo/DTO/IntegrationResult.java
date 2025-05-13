package com.itmo.DTO;

import java.text.DecimalFormat;

public class IntegrationResult {
    private final double result;
    private final int n;

    public IntegrationResult(double result, int n) {
        this.result = result;
        this.n = n;
    }

    public String printResult() {
        DecimalFormat df = new DecimalFormat("0.######");

        return String.format(
                """
                        Значение интеграла: %s
                        Количество разбиений: %s""",
                df.format(result),
                df.format(n)
        );
    }
}
