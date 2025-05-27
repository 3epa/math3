package com.itmo;

import com.itmo.DTO.FunctionHolder;
import com.itmo.DTO.IntegrationResult;
import com.itmo.methods.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        FunctionHolder[] holders = {
                new FunctionHolder("x^2", x -> x * x),
                new FunctionHolder("sin(x)", Math::sin),
                new FunctionHolder("e^x", Math::exp),
                new FunctionHolder("10", x -> 10.0),
                new FunctionHolder("2x^3-9x^2-7x+11", x -> 2*x*x*x-9*x*x-7*x+11),
        };
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Доступные функции");
            for (int i = 0; i < holders.length; i++) {
                System.out.println((i+1) + " " + holders[i].getName());
            }
            System.out.println("Выберите функцию (1-" + holders.length + "):");
            int functionChoice = Integer.parseInt(reader.readLine());

            System.out.println("Доступные методы интегрирования:");
            System.out.println("1. Метод левых прямоугольников");
            System.out.println("2. Метод правых прямоугольников");
            System.out.println("3. Метод средних прямоугольников");
            System.out.println("4. Метод трапеций");
            System.out.println("5. Метод Симпсона");

            int methodChoice = Integer.parseInt(reader.readLine());

            System.out.println("Введите точность:");
            double epsilon = Double.parseDouble(reader.readLine().replaceAll(",", "."));
            Integrator integrator = createIntegrator(methodChoice, epsilon, holders[functionChoice-1].getFunction());

            System.out.println("Введите левую границу:");
            double a = Double.parseDouble(reader.readLine().replaceAll(",", "."));
            System.out.println("Введите правую границу:");
            double b = Double.parseDouble(reader.readLine().replaceAll(",", "."));

            IntegrationResult result = integrator.integrate(a, b);
            System.out.println(result.getFormattedResult());
        } catch (IOException e) {
            System.out.println("Внутренняя ошибка, попробуйте перезапустить приложение");
        } catch (NumberFormatException e) {
            System.out.println("Одно из введенных значений не является числом");
        }
    }

    private static Integrator createIntegrator(int methodChoice, double epsilon, Function<Double, Double> function) {
        return switch (methodChoice) {
            case 1 -> new LeftRectangleMethod(epsilon, function);
            case 2 -> new RightRectangleMethod(epsilon, function);
            case 3 -> new CenterRectangleMethod(epsilon, function);
            case 4 -> new TrapezoidMethod(epsilon, function);
            case 5 -> new SimpsonMethod(epsilon, function);
            default -> throw new IllegalArgumentException("Неверный выбор метода интегрирования");
        };
    }
}