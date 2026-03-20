package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import engine.CalculatorEngine;
import ui.DisplayPanel;

public class ButtonController implements ActionListener {
    private DisplayPanel display;
    private CalculatorEngine engine;

    public ButtonController(DisplayPanel display) {
        this.display = display;
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
            display.appendText(value);

        } else if (value.equals("%")) {
            display.appendText("%");

        } else if (value.equals("mod")) {
            display.appendText(" mod ");
            
        } else if (value.equals("=")) {
            try {
                String expression = display.getText();
                double result = engine.evaluate(expression);
                display.setText(formatResult(result));

            } catch (Exception ex) {
                display.setText("Error");
            }
        }
    }

    private boolean isOperator(String value) {
        return value.matches("[+\\-*/÷×]");
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