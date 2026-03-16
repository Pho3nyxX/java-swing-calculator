package ui;

import javax.swing.*;
import java.awt.*;

public class CalculatorFrame extends JFrame {
    private TopPanel topPanel;
    private HistoryPanel historyPanel;
    private DisplayPanel displayPanel;
    private ButtonPanel buttonPanel;
    
    public CalculatorFrame(){
        initializeFrame();
        initializePanels();
        layoutPanels();

        setVisible(true);
    }

    private void initializeFrame(){
        setTitle("Calculator");
        setSize(410, 700);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initializePanels(){
        topPanel = new TopPanel();
        historyPanel = new HistoryPanel();
        displayPanel = new DisplayPanel();
        buttonPanel = new ButtonPanel();
    }

    private void layoutPanels(){
        add(topPanel, BorderLayout.NORTH);
        add(historyPanel, BorderLayout.WEST);
        add(displayPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
