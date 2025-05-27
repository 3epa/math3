package com.itmo.methods;

import com.itmo.DTO.FunctionHolder;

import java.util.function.Function;

public class RightRectangleMethod extends Method {
    protected String name = "Метод правых прямоугольников";
    public RightRectangleMethod(double epsilon, FunctionHolder f) {
        super(epsilon, f);
    }

    @Override
    protected double doIntegration(double a, double b, int n) {
        double h = (b - a) / n;
        double sum = 0;
        for (int i = 1; i <= n; i++) {
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
