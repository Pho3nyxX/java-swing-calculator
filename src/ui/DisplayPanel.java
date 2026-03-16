package ui;

import javax.swing.*;
import java.awt.*;

public class DisplayPanel extends JPanel {
    private JTextField displayField;

    public DisplayPanel() {
        initializeComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        displayField = new JTextField();
        displayField.setFont(new Font("Arial", Font.BOLD, 24));
        displayField.setHorizontalAlignment(JTextField.RIGHT);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());
        add(displayField, BorderLayout.CENTER);
    }
}