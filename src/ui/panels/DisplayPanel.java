package ui.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import controller.ButtonController;

public class DisplayPanel extends JPanel {
    private JTextField display;
    private ButtonController controller;

    public DisplayPanel() {
        initializeComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.LEFT);

        display.setBackground(theme.CalculatorTheme.displayBackground);
        display.setForeground(Color.WHITE);
        display.setCaretColor(Color.WHITE);
        display.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

        setPreferredSize(new Dimension(0, 90));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));

        display.setFocusable(true);

        display.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                e.consume(); 
            }

            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());
        add(display, BorderLayout.CENTER);
    }

    private void handleKeyPress(KeyEvent e) {
        if (controller == null)
            return;

        String value = null;
        char keyChar = e.getKeyChar();
        int keyCode = e.getKeyCode();

        if (Character.isDigit(keyChar) || keyChar == '.') {
            value = String.valueOf(keyChar);

        } else if (keyChar == '+' || keyChar == '-' || keyChar == '*' || keyChar == '/') {
            value = String.valueOf(keyChar);

        } else if (keyChar == '(' || keyChar == ')') {
            value = String.valueOf(keyChar);

        } else if (keyChar == '%') {
            value = "%";

        } else if (keyCode == KeyEvent.VK_ENTER) {
            value = "=";

        } else if (keyCode == KeyEvent.VK_BACK_SPACE) {
            String text = getText();
            if (!text.isEmpty()) {
                setText(text.substring(0, text.length() - 1));
            }
            return;
        }

        if (value != null) {
            controller.actionPerformed(
                    new ActionEvent(display, ActionEvent.ACTION_PERFORMED, value));
        }
    }

    public void appendText(String text) {
        display.setText(display.getText() + text);
        display.requestFocusInWindow();
    }

    public void requestFocusForInput() {
        display.requestFocusInWindow();
    }

    public void clear() {
        display.setText("");
    }

    public void setText(String text) {
        display.setText(text);
    }

    public String getText() {
        return display.getText();
    }

    public void setController(ButtonController controller) {
        this.controller = controller;
    }
}