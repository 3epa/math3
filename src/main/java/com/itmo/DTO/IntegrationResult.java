package com.itmo.DTO;

import java.text.DecimalFormat;

public class IntegrationResult {
    private final double a;
    private final double b;
    private final FunctionHolder function;
    private final String methodName;
    private final double result;
    private final int n;

    public IntegrationResult(double a, double b, FunctionHolder function, String methodName, double result, int n) {
        this.a = a;
        this.b = b;
        this.function = function;
        this.methodName = methodName;
        this.result = result;
        this.n = n;
    }

    public String getFormattedResult() {
        DecimalFormat df = new DecimalFormat("0.######");

        return String.format(
                """
                        =====Введенные данные=====
                        Выбранная функция: %s,
                        Выбранный метод: %s,
                        Левая граница: %s,
                        Правая граница: %s,
                        
                        =====Ответ=====
                        Значение интеграла: %s
                        Количество разбиений: %s""",
                function,
                methodName,
                df.format(a),
                df.format(b),
                df.format(result),
                df.format(n)
        );
    }
}
