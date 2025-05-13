package com.itmo.methods;

import com.itmo.DTO.IntegrationResult;

import java.util.function.Function;

public abstract class Method implements Integrator {
    protected final double epsilon;
    protected final int k = 1;
    protected double sign = 1;

    protected Function<Double, Double> f;

    public Method(double epsilon, Function<Double, Double> f) {
        this.epsilon = epsilon;
        this.f = f;
    }

    @Override
    public IntegrationResult integrate(double a, double b) {
        if (a > b) {
            this.sign *= -1;
            double temp = a;
            a = b;
            b = temp;
        }
        double prevResult;
        int n = 4;
        double result = doIntegration(a, b, n);
        do {
            n *= 2;
            prevResult = result;
            result = doIntegration(a, b, n);
        } while (!isSolved(prevResult, result));
        return new IntegrationResult(sign * result, n);
    }
    protected abstract double doIntegration(double a, double b, int n);

    protected boolean isSolved(double I1, double I2) {
        return Math.abs(I1 - I2) / (Math.pow(2, k) - 1) <= epsilon;
    }
}
