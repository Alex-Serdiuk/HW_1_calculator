package com.example.hw_1_calculator;

public class Calculator {
    private final String operator;

    public Calculator(String operator) {
        this.operator = operator;
    }

    public double calculations(double... a) {

        switch (operator) {
            case "√":
            {
                return Math.sqrt(a[0]);
            }
            case "^":
            {
                return Math.pow(a[0], a[1]);
            }
            case "✕":
            case "*":
            {
                return a[0] * a[1];
            }
            case "÷":
            case "/":
            {
                return a[0] / a[1];
            }
            case "+":
            {
                return a[0] + a[1];
            }
            case "-":
            {
                return a[0] - a[1];
            }
        }
        return 0;
    }
}
