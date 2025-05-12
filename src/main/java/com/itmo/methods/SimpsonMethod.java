package com.itmo.methods;

import java.util.function.Function;

public class SimpsonMethod extends Method {
    protected final int k = 4;

    public SimpsonMethod(double epsilon, Function<Double, Double> f) {
        super(epsilon, f);
    }

    @Override
    protected double doIntegration(double a, double b, int n) {
        double h = (b - a) / n;
        double sum = f.apply(a) + f.apply(b);
        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            if (i % 2 == 0) {
                sum += 2*f.apply(x);
            } else {
                sum += 4*f.apply(x);
            }
        }
        sum *= h/3;
        return sum;
    }
}
