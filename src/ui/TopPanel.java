package ui;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {
    private JComboBox<String> modeDropdown;
    private JButton minimizeButton;
    private JButton maximizeButton;
    private JButton closeButton;
    private String[] modes;
    private JPanel buttonPanel;

    public TopPanel() {
        initializeComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        modes = new String[] { "Basic", "Advanced", "Programming" };
        modeDropdown = new JComboBox<>(modes);
        modeDropdown.setFont(new Font("Arial", Font.BOLD, 16));
        modeDropdown.setBorder(BorderFactory.createEmptyBorder());
        modeDropdown.setBackground(theme.CalculatorTheme.buttonPanelBackground);
        modeDropdown.setForeground(Color.WHITE);

        modeDropdown.setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton arrow = super.createArrowButton();
                arrow.setBorder(BorderFactory.createEmptyBorder());
                arrow.setContentAreaFilled(false);
                arrow.setBackground(theme.CalculatorTheme.buttonPanelBackground);
                arrow.setForeground(Color.WHITE);
                return arrow;
            }
        });

        minimizeButton = new CircleButton("_");
        maximizeButton = new CircleButton("□");
        closeButton = new CircleButton("X");
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());
        setBackground(theme.CalculatorTheme.buttonPanelBackground);
        setBorder(BorderFactory.createEmptyBorder(12, 10, 12, 10));

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(minimizeButton);
        buttonPanel.add(maximizeButton);
        buttonPanel.add(closeButton);

        add(modeDropdown, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.EAST);
    }
}
