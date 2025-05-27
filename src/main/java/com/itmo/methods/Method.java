package com.itmo.methods;

import com.itmo.DTO.FunctionHolder;
import com.itmo.DTO.IntegrationResult;

public abstract class Method implements Integrator {
    protected String name;
    protected final double epsilon;
    protected final int k = 2;
    protected double sign = 1;
    private final int MAX_ITERATIONS = 1_000_000_000;

    protected FunctionHolder f;

    public Method(double epsilon, FunctionHolder f) {
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
            if (n > MAX_ITERATIONS) {
                throw new RuntimeException("Слишком много итераций, не удалось достичь требуемой точности");
            }
        } while (!isSolved(prevResult, result));
        result = sign * result;
        return new IntegrationResult(a, b, f, getName(), result, n);
    }

    protected abstract double doIntegration(double a, double b, int n);

    protected boolean isSolved(double I1, double I2) {
        return Math.abs(I1 - I2) / (Math.pow(2, getK()) - 1) <= epsilon;
    }

    public String getName() {
        return name;
    }

    public int getK() {
        return k;
    }
}
