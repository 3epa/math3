package com.itmo.methods;

import com.itmo.DTO.FunctionHolder;

public class TrapezoidMethod extends Method {
    protected String name = "Метод трапеций";
    public TrapezoidMethod(double epsilon, FunctionHolder f) {
        super(epsilon, f);
    }

    @Override
    protected double doIntegration(double a, double b, int n) {
        double h = (b - a) / n;
        double sum = (f.getFunction().apply(a) + f.getFunction().apply(b))/2;
        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            sum += f.getFunction().apply(x);
        }
        sum *= h;
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
