package com.itmo.methods;

import com.itmo.DTO.FunctionHolder;

import java.util.function.Function;

public class SimpsonMethod extends Method {
    protected String name = "Метод Симпсона";
    protected final int k = 4;

    public SimpsonMethod(double epsilon, FunctionHolder f) {
        super(epsilon, f);
    }

    @Override
    protected double doIntegration(double a, double b, int n) {
        double h = (b - a) / n;
        double sum = f.getFunction().apply(a) + f.getFunction().apply(b);
        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            if (i % 2 == 0) {
                sum += 2 * f.getFunction().apply(x);
            } else {
                sum += 4 * f.getFunction().apply(x);
            }
        }
        sum *= h / 3;
        return sum;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getK() {
        return super.getK();
    }
}
