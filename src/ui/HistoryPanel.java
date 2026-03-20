package ui;

import javax.swing.*;
import java.awt.*;

public class HistoryPanel extends JPanel {
    private JTextArea history;
    private JScrollPane scrollPane;

    public HistoryPanel() {
        initializeComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        history = new JTextArea();
        history.setEditable(false);

        scrollPane = new JScrollPane(history);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setViewportBorder(null);
        scrollPane.getVerticalScrollBar().setBorder(null);
        scrollPane.getHorizontalScrollBar().setBorder(null);

        history.setBackground(theme.CalculatorTheme.historyBackground);
        history.setForeground(Color.WHITE);
        history.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

        setPreferredSize(new Dimension(0, 100));
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    public void addEntry(String entry) {
        history.append(entry + "\n");
        history.setCaretPosition(history.getDocument().getLength());
    }
}