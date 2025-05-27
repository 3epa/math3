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
                new FunctionHolder("2x^3-9x^2-7x+11", x -> 2 * x * x * x - 9 * x * x - 7 * x + 11),
        };
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Доступные функции");
            for (int i = 0; i < holders.length; i++) {
                System.out.println((i + 1) + " " + holders[i].getName());
            }

            System.out.println("Выберите функцию (1-" + holders.length + "):");
            int functionChoice = chooseValue(reader);


            System.out.println("Доступные методы интегрирования:");
            System.out.println("1. Метод левых прямоугольников");
            System.out.println("2. Метод правых прямоугольников");
            System.out.println("3. Метод средних прямоугольников");
            System.out.println("4. Метод трапеций");
            System.out.println("5. Метод Симпсона");

            int methodChoice = chooseValue(reader);;

            System.out.println("Введите точность:");
            double epsilon = inputValue(reader);
            Integrator integrator = createIntegrator(methodChoice, epsilon, holders[functionChoice - 1]);

            System.out.println("Введите левую границу:");
            double a = inputValue(reader);
            System.out.println("Введите правую границу:");
            double b = inputValue(reader);

            IntegrationResult result = integrator.integrate(a, b);
            System.out.println(result.getFormattedResult());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static double inputValue(BufferedReader reader) {
        double value;
        while (true) {
            try {
                value = Double.parseDouble(reader.readLine().replaceAll(",", "."));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Одно из введенных значений не является числом");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return value;
    }

    private static int chooseValue(BufferedReader reader) {
        int value;
        while (true) {
            try {
                value = Integer.parseInt(reader.readLine());
                if (value <= 0 || value >= 6) {
                    throw new Exception("Введите значение от 1 до 5");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Одно из введенных значений не является числом");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return value;
    }

    private static Integrator createIntegrator(int methodChoice, double epsilon, FunctionHolder function) {
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