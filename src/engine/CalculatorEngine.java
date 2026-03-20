package engine;

import java.util.Stack;

public class CalculatorEngine {
    public double evaluate(String expression) {
        expression = preprocess(expression);
        return evaluateExpression(expression);
    }

    private String preprocess(String expr) {
        return expr
                .replace("×", "*")
                .replace("÷", "/")
                .replace("π", String.valueOf(Math.PI));
    }

    private double evaluateExpression(String expr) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expr.length(); i++) {
            char currentChar = expr.charAt(i);

            if (currentChar == ' ')
                continue;

            if (Character.isDigit(currentChar) || currentChar == '.') {
                StringBuilder num = new StringBuilder();

                while (i < expr.length() &&
                        (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.')) {
                    num.append(expr.charAt(i));
                    i++;
                }
                numbers.push(Double.parseDouble(num.toString()));
                i--;

            } else if (currentChar == '(') {
                operators.push(currentChar);

            } else if (currentChar == ')') {
                while (operators.peek() != '(') {
                    numbers.push(applyOperation(
                            operators.pop(),
                            numbers.pop(),
                            numbers.pop()));
                }
                operators.pop();

            } else if (isOperator(currentChar)) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(currentChar)) {
                    numbers.push(applyOperation(
                            operators.pop(),
                            numbers.pop(),
                            numbers.pop()));
                }
                operators.push(currentChar);
            }
        }

        while (!operators.isEmpty()) {
            numbers.push(applyOperation(
                    operators.pop(),
                    numbers.pop(),
                    numbers.pop()));
        }
        return numbers.pop();
    }

    private boolean isOperator(char currentChar) {
        return currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/';
    }

    private int precedence(char operator) {
        if (operator == '+' || operator == '-')
            return 1;
        if (operator == '*' || operator == '/')
            return 2;
        return 0;
    }

    private double applyOperation(char operator, double b, double a) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return b != 0 ? a / b : 0;
        }
        return 0;
    }
}