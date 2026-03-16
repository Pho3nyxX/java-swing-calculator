package ui;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {
    private JComboBox<String> modeDropdown;
    private JButton minimizeButton;
    private JButton maximizeButton;
    private JButton closeButton;
    private String[] modes;

    public TopPanel() {
        initializeComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        modes = new String[] { "Basic", "Advanced", "Programming" };
        modeDropdown = new JComboBox<>(modes);
        minimizeButton = new JButton("_");
        maximizeButton = new JButton("□");
        closeButton = new JButton("X");
    }

     private void layoutComponents() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(modeDropdown);
        add(minimizeButton);
        add(maximizeButton);
        add(closeButton);
    }
}
