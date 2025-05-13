package com.itmo.DTO;

import java.util.function.Function;

public class FunctionHolder {
    private final String name;
    private final Function<Double, Double> function;

    public FunctionHolder(String name, Function<Double, Double> function) {
        this.name = name;
        this.function = function;
    }

    public String getName() {
        return name;
    }

    public Function<Double, Double> getFunction() {
        return function;
    }
}
