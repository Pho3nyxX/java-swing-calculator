package ui;

import javax.swing.*;
import java.awt.*;

public class DisplayPanel extends JPanel {
    private JTextField display;

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
        display.setBorder(BorderFactory.createEmptyBorder());

        setPreferredSize(new Dimension(0, 90));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());
        add(display, BorderLayout.CENTER);
    }

    public void appendText(String text){
        display.setText(display.getText() + text);
    }

    public void setText(String text){
        display.setText(text);
    }

    public String getText(){
        return display.getText();
    }
}