package com.janwarlen.ac.others;

import java.util.Stack;

// Evaluate Reverse Polish Notation
public class EvaluateReversePolishNotation {

    Stack<String> expr = new Stack<>();

    public int evalRPN(String[] tokens) {
        for (String token : tokens) {
            if (checkOperator(token)) {
                String num2 = expr.pop();
                String num1 = expr.pop();
                expr.push(cal(num1, num2, token));
            } else {
                expr.push(token);
            }
        }
        return Integer.parseInt(expr.pop());
    }

    private String cal(String num1, String num2, String operator) {
        int a = Integer.parseInt(num1), b = Integer.parseInt(num2);
        switch (operator) {
            case "+":
                return String.valueOf(a + b);
            case "-":
                return String.valueOf(a - b);
            case "*":
                return String.valueOf(a * b);
            case "/":
                return String.valueOf(a / b);
            default:
                return "0";
        }
    }

    private boolean checkOperator(String token) {
        return "+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token);
    }
}
