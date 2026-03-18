package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import theme.CalculatorTheme;
import controller.ButtonController;

public class ButtonPanel extends JPanel {
    public List<JButton> buttons = new ArrayList<>();
    private ButtonController controller;

    public ButtonPanel(DisplayPanel display) {
        setLayout(new GridBagLayout());
        setBackground(theme.CalculatorTheme.buttonPanelBackground);
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        controller = new ButtonController(display);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(2, 2, 2, 2);

        initializeButtons(gbc);
    }

    private void initializeButtons(GridBagConstraints gbc) {
        addButton("C", 0, 0, 1, 1, gbc);
        addButton("(", 1, 0, 1, 1, gbc);
        addButton(")", 2, 0, 1, 1, gbc);
        addButton("mod", 3, 0, 1, 1, gbc);
        addButton("π", 4, 0, 1, 1, gbc);

        addButton("7", 0, 1, 1, 1, gbc);
        addButton("8", 1, 1, 1, 1, gbc);
        addButton("9", 2, 1, 1, 1, gbc);
        addButton("÷", 3, 1, 1, 1, gbc);
        addButton("√", 4, 1, 1, 1, gbc);

        addButton("4", 0, 2, 1, 1, gbc);
        addButton("5", 1, 2, 1, 1, gbc);
        addButton("6", 2, 2, 1, 1, gbc);
        addButton("×", 3, 2, 1, 1, gbc);
        addButton("x²", 4, 2, 1, 1, gbc);

        addButton("1", 0, 3, 1, 1, gbc);
        addButton("2", 1, 3, 1, 1, gbc);
        addButton("3", 2, 3, 1, 1, gbc);
        addButton("-", 3, 3, 1, 1, gbc);

        addButton("0", 0, 4, 1, 1, gbc);
        addButton(".", 1, 4, 1, 1, gbc);
        addButton("%", 2, 4, 1, 1, gbc);
        addButton("+", 3, 4, 1, 1, gbc);

        addButton("=", 4, 3, 1, 2, gbc);
    }

    private void addButton(String text, int x, int y, int width, int height, GridBagConstraints gbc) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;

        gbc.ipadx = 10;
        gbc.ipady = 15;

        RoundedButton button;

        if (text.equals("=")) {
            button = new RoundedButton(text, CalculatorTheme.equalsButton, CalculatorTheme.equalsButtonHover);
            button.setForeground(Color.BLACK);
        } else {
            button = new RoundedButton(text);
            button.setForeground(Color.BLACK);
        }

        button.setFont(new Font("Arial", Font.BOLD, 18));
        
        button.addActionListener(controller);

        buttons.add(button);
        add(button, gbc);
    }
}