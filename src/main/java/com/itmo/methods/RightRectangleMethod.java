package com.itmo.methods;

import java.util.function.Function;

public class RightRectangleMethod extends Method {
    public RightRectangleMethod(double epsilon, Function<Double, Double> f) {
        super(epsilon, f);
    }

    @Override
    protected double doIntegration(double a, double b, int n) {
        double h = (b - a) / n;
        double sum = 0;
        for (int i = 1; i <= n; i++) {
            double x = a + i * h;
            sum += f.apply(x);
        }
        sum *= h;
        return sum;
    }
}
