package ui;

import javax.swing.*;
import java.awt.*;

public class CalculatorFrame extends JFrame {
    private TopPanel topPanel;
    private HistoryPanel historyPanel;
    private DisplayPanel displayPanel;
    private ButtonPanel buttonPanel;
    private JPanel topContainer;

    public CalculatorFrame() {
        initializeFrame();
        initializePanels();
        layoutPanels();

        setVisible(true);
    }

    private void initializeFrame() {
        setTitle("Calculator");
        setSize(410, 700);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initializePanels() {
        topContainer = new JPanel();
        topContainer.setLayout(new BoxLayout(topContainer, BoxLayout.Y_AXIS));

        topPanel = new TopPanel();
        historyPanel = new HistoryPanel();
        displayPanel = new DisplayPanel();
        buttonPanel = new ButtonPanel();;
    }

    private void layoutPanels() {
        topContainer.add(topPanel);
        topContainer.add(historyPanel);
        topContainer.add(displayPanel);

        add(topContainer, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
