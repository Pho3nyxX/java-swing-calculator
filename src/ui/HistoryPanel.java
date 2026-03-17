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

        // ✅ REMOVE scrollpane border (this is the main fix)
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        // ✅ REMOVE viewport border (important)
        scrollPane.setViewportBorder(null);
        // ✅ OPTIONAL: remove scrollbar spacing feel
        scrollPane.getVerticalScrollBar().setBorder(null);
        scrollPane.getHorizontalScrollBar().setBorder(null);

        history.setBackground(theme.CalculatorTheme.historyBackground);
        history.setForeground(Color.WHITE);
        history.setBorder(BorderFactory.createEmptyBorder());

        setPreferredSize(new Dimension(0, 100));
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }
}