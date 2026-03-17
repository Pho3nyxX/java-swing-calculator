package ui;

import javax.swing.*;
import java.awt.*;

public class HistoryPanel extends JPanel {
    private JTextArea historyArea;
    private JScrollPane scrollPane;

    public HistoryPanel() {
        initializeComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        historyArea = new JTextArea();
        historyArea.setEditable(false);
        scrollPane = new JScrollPane(historyArea);
        // historyArea.setBackground(Color.RED);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }
}