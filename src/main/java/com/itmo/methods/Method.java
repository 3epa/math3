package com.itmo.methods;

import java.util.function.Function;

public abstract class Method implements Integrator {
    protected final double epsilon;
    protected final double k = 1;

    protected Function<Double, Double> f;

    public Method(double epsilon, Function<Double, Double> f) {
        this.epsilon = epsilon;
        this.f = f;
    }

    @Override
    public double integrate(double a, double b, int n) {
        double prevResult;
        double result = doIntegration(a, b, n);
        do {
            n *= 2;
            prevResult = result;
            result = doIntegration(a, b, n);
        } while (!isSolved(prevResult, result));
        return result;
    }
    protected abstract double doIntegration(double a, double b, int n);

    protected boolean isSolved(double I1, double I2) {
        return Math.abs(I1 - I2) / (Math.pow(2, k) - 1) <= epsilon;
    }
}
