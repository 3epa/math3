package com.itmo.methods;

import java.util.function.Function;

public class CenterRectangleMethod extends Method {
    public CenterRectangleMethod(double epsilon, Function<Double, Double> f) {
        super(epsilon, f);
    }

    @Override
    protected double doIntegration(double a, double b, int n) {
        double h = (b - a) / n;
        double sum = 0;
        for (int i = 0; i < n; i++) {
            double x = a + (i+0.5) * h;
            sum += f.apply(x);
        }
        sum *= h;
        return sum;
    }

}
