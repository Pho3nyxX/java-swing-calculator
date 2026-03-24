package ui;

import javax.swing.*;
import java.awt.*;
import controller.ButtonController;
import ui.panels.ButtonPanel;
import ui.panels.DisplayPanel;
import ui.panels.HistoryPanel;
import ui.panels.TopPanel;

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
        displayPanel.requestFocusForInput();
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

        ButtonController controller = new ButtonController(displayPanel, historyPanel);

        displayPanel.setController(controller);

        buttonPanel = new ButtonPanel(displayPanel, controller);
    }

    private void layoutPanels() {
        centerContainer.add(historyPanel, BorderLayout.CENTER);
        centerContainer.add(displayPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(centerContainer, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
