package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import engine.CalculatorEngine;
import ui.DisplayPanel;

public class ButtonController implements ActionListener {
    private DisplayPanel display;
    private CalculatorEngine engine;
    private boolean isOperatorClicked;
    private String currentOperator = "";
    private double firstNumber = 0;

    public ButtonController(DisplayPanel display) {
        this.display = display;
        this.engine = new CalculatorEngine();
        this.isOperatorClicked = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String value = e.getActionCommand().trim();

        if (isNumber(value) || value.equals(".")) {
            display.appendText(value);
            isOperatorClicked = false;

        } else if(value.equals("C")){
            display.setText("");
            firstNumber = 0;
            currentOperator = "";
            isOperatorClicked = false;

        }else if (value.equals("π")) {
            if (!display.getText().isEmpty()
                    && Character.isDigit(display.getText().charAt(display.getText().length() - 1))) {
                display.appendText("π");
            } else {
                display.appendText("π");
            }

        } else if (value.equals("x²")) {
            display.appendText("²");

        } else if (value.equals("√")) {
            double number = parseDisplayNumber();
            display.setText(formatResult(Math.sqrt(number)));

        } else if (isOperator(value) || value.equals("mod")) {
            if (isOperatorClicked)
                return;

            firstNumber = parseDisplayNumber();
            currentOperator = value;
            engine.setoperator(firstNumber, currentOperator);
            display.appendText(" " + value + " ");
            isOperatorClicked = true;

        } else if (value.equals("%")) {
            display.appendText("%");

        } else if (value.equals("=")) {
            try {
                String text = display.getText().trim();

                if (!text.contains(" ")) {
                    double result = parseDisplayNumber();
                    if (text.endsWith("%")) {
                        display.setText(String.format("%.2f", result));
                    } else {
                        display.setText(formatResult(result));
                    }
                    return;
                }

                double secondNumber = parseSecondNumber();
                double result = engine.calculate(secondNumber);
                display.setText(formatResult(result));

            } catch (Exception ex) {
                display.setText("Error");
            }
        }
    }

    private boolean isNumber(String value) {
        return value.matches("[0-9]");
    }

    private boolean isOperator(String value) {
        return value.matches("[+\\-*/÷×]");
    }

    private double parseDisplayNumber() {
        String text = display.getText().trim();
        if (text.contains(" "))
            text = text.split(" ")[0];
        return parseNumber(text);
    }

    private double parseSecondNumber() {
        String text = display.getText().trim().split(" ")[2];
        return parseNumber(text);
    }

    private double parseNumber(String text) {
        text = text.trim();

        if (text.endsWith("²")) {
            double base = Double.parseDouble(text.substring(0, text.length() - 1));
            return base * base;
        }

        if (text.endsWith("%")) {
            double base = Double.parseDouble(text.substring(0, text.length() - 1));
            return base / 100;
        }

        if (text.equals("π"))
            return Math.PI;

        if (text.contains("π")) {
            String expr = text.replaceAll("(?<=\\d)π", "*" + Math.PI)
                    .replace("π", String.valueOf(Math.PI));
            return evalSimpleExpression(expr);
        }

        if (text.startsWith("√")) {
            String base = text.substring(1);
            double value = parseNumber(base);
            return Math.sqrt(value);
        }

        return Double.parseDouble(text);
    }

    private double evalSimpleExpression(String expr) {
        String[] parts = expr.split("\\*");
        double result = 1;
        for (String part : parts) {
            result *= Double.parseDouble(part);
        }
        return result;
    }

    private String formatResult(double result) {
        if (result == (long) result) {
            return String.valueOf((long) result);
        } else {
            return String.format("%.9f", result);
        }
    }
}