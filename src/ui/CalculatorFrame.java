package ui;

import javax.swing.*;
import java.awt.*;

public class CalculatorFrame extends JFrame {
    private TopPanel topPanel;
    private HistoryPanel historyPanel;
    private DisplayPanel displayPanel;
    private ButtonPanel buttonPanel;
    private JPanel centerContainer;

    public CalculatorFrame() {
        initializeFrame();
        initializePanels();
        layoutPanels();

        setVisible(true);
    }

    private void initializeFrame() {
        setUndecorated(true);
        setSize(410, 700);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initializePanels() {
        centerContainer = new JPanel();
        centerContainer.setLayout(new BorderLayout());

        topPanel = new TopPanel(this);
        historyPanel = new HistoryPanel();
        displayPanel = new DisplayPanel();
        buttonPanel = new ButtonPanel(displayPanel);
    }

    private void layoutPanels() {
        centerContainer.add(historyPanel, BorderLayout.CENTER); 
        centerContainer.add(displayPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH); 
        add(centerContainer, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
