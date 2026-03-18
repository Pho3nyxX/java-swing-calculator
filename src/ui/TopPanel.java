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

    public TopPanel(JFrame parentFrame) {
        initializeComponents(parentFrame);
        layoutComponents();
        addDragFunctionality(parentFrame);
    }

    private void addDragFunctionality(JFrame parentFrame) {
        final Point[] initialClick = { null };

        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                initialClick[0] = e.getPoint();
            }
        });

        this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseDragged(java.awt.event.MouseEvent e) {
                if (initialClick[0] != null) {
                    int thisX = parentFrame.getLocation().x;
                    int thisY = parentFrame.getLocation().y;

                    int xMoved = e.getX() - initialClick[0].x;
                    int yMoved = e.getY() - initialClick[0].y;

                    int X = thisX + xMoved;
                    int Y = thisY + yMoved;
                    parentFrame.setLocation(X, Y);
                }
            }
        });
    }

    private void initializeComponents(JFrame parentFrame) {
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

        minimizeButton.addActionListener(e -> parentFrame.setState(JFrame.ICONIFIED));

        maximizeButton.addActionListener(e -> {
            if ((parentFrame.getExtendedState() & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
                parentFrame.setExtendedState(JFrame.NORMAL);
            } else {
                parentFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });

        closeButton.addActionListener(e -> parentFrame.dispose());
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
