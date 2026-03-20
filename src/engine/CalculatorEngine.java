package engine;

import java.util.Stack;

public class CalculatorEngine {
    public double evaluate(String expression) {
        expression = preprocess(expression);
        return evaluateExpression(expression);
    }

    private String preprocess(String expr) {
        expr = expr
                .replace("×", "*")
                .replace("÷", "/")
                .replace("mod", "%");

        expr = expr.replaceAll("(\\d)π", "$1*" + Math.PI);
        expr = expr.replaceAll("π", String.valueOf(Math.PI));
        expr = expr.replaceAll("(\\d)\\(", "$1*(");
        expr = expr.replaceAll("\\)\\(", ")*(");
        expr = expr.replaceAll("\\)(\\d)", ")*$1");

        expr = expr.replaceAll("\\)²", ")^2");
        expr = expr.replaceAll("(\\d+(\\.\\d+)?)²", "($1*$1)");
        expr = expr.replaceAll("√(\\d+(\\.\\d+)?)", "($1^0.5)");
        expr = expr.replaceAll("(\\d+(\\.\\d+)?)%", "($1/100)");

        expr = expr.replaceAll("√\\(", "sqrt(");

        return expr;
    }

    private double evaluateExpression(String expr) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expr.length(); i++) {
            char currentChar = expr.charAt(i);

            if (currentChar == ' ')
                continue;

            if (i + 3 < expr.length() && expr.substring(i, i + 4).equals("sqrt")) {
                operators.push('s');
                i += 3;
                continue;

            } else if (Character.isDigit(currentChar) || currentChar == '.') {
                StringBuilder num = new StringBuilder();

                while (i < expr.length() && (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.')) {
                    num.append(expr.charAt(i));
                    i++;
                }
                numbers.push(Double.parseDouble(num.toString()));
                i--;

            } else if (currentChar == '(') {
                operators.push('(');

            } else if (currentChar == ')') {
                while (operators.peek() != '(') {
                    numbers.push(applyOperation(
                            operators.pop(),
                            numbers.pop(),
                            numbers.pop()));
                }
                operators.pop();

                if (!operators.isEmpty() && operators.peek() == 's') {
                    double val = numbers.pop();
                    numbers.push(Math.sqrt(val));
                    operators.pop();
                }

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
        return currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/'
                || currentChar == '^' || currentChar == '%';
    }

    private int precedence(char operator) {
        if (operator == '+' || operator == '-')
            return 1;
        if (operator == '*' || operator == '/' || operator == '%')
            return 2;
        if (operator == '^')
            return 3;
        return 0;
    }

    private double applyOperation(char operator, double rightOperand, double leftOperand) {
        switch (operator) {
            case '+':
                return leftOperand + rightOperand;
            case '-':
                return leftOperand - rightOperand;
            case '*':
                return leftOperand * rightOperand;
            case '/':
                return rightOperand != 0 ? leftOperand / rightOperand : 0;
            case '%':
                return leftOperand % rightOperand;
            case '^':
                return Math.pow(leftOperand, rightOperand);
        }
        return 0;
    }
}