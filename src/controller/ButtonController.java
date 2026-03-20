package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import engine.CalculatorEngine;
import ui.DisplayPanel;
import ui.HistoryPanel;

public class ButtonController implements ActionListener {
    private DisplayPanel display;
    private HistoryPanel history;
    private CalculatorEngine engine;

    public ButtonController(DisplayPanel display, HistoryPanel history) {
        this.display = display;
        this.history = history;
        this.engine = new CalculatorEngine();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String value = e.getActionCommand().trim();

        if (isInputCharacter(value)) {
            display.appendText(value);

        } else if (value.equals("C")) {
            display.clear();
            return;

        } else if (value.equals("π")) {
            display.appendText("π");

        } else if (value.equals("x²")) {
            display.appendText("²");

        } else if (value.equals("√")) {
            display.appendText("√");

        } else if (isOperator(value)) {
            String text = display.getText();

            if (!text.isEmpty() && isOperator(text.charAt(text.length() - 1))) {
                text = text.substring(0, text.length() - 1);
            }

            display.setText(text + value);

        } else if (value.equals("%")) {
            display.appendText("%");

        } else if (value.equals("mod")) {
            display.appendText(" mod ");

        } else if (value.equals("=")) {
            try {
                String expression = display.getText();
                double result = engine.evaluate(expression);
                String formatted = formatResult(result);

                display.setText(formatted);
                history.addEntry(expression + " = " + formatted);

            } catch (Exception ex) {
                display.setText("Error");
            }
        }
    }

    private boolean isOperator(String value) {
        return value.matches("[+\\-*/÷×]");
    }

    private boolean isOperator(char currentChar) {
        return currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/'
                || currentChar == '×' || currentChar == '÷' || currentChar == '%';
    }

    private boolean isInputCharacter(String value) {
        return value.matches("[0-9.]+") || value.equals("(") || value.equals(")");
    }

    private String formatResult(double result) {
        if (result == (long) result) {
            return String.valueOf((long) result);
        } else {
            return String.format("%.9f", result);
        }
    }
}