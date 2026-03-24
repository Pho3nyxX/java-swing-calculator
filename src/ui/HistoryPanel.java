package ui;

import javax.swing.*;
import java.awt.*;

public class HistoryPanel extends JPanel {
    private JPanel history;
    private JPanel historyWrapper;
    private JScrollPane scrollPane;
    private static final Font HISTORY_FONT = new Font("Arial", Font.PLAIN, 24);

    public HistoryPanel() {
        initializeComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        history = new JPanel();
        history.setLayout(new BoxLayout(history, BoxLayout.Y_AXIS));
        history.setOpaque(false);

        historyWrapper = new JPanel(new BorderLayout());
        historyWrapper.setBackground(theme.CalculatorTheme.historyBackground);
        history.add(Box.createVerticalGlue());
        historyWrapper.add(history, BorderLayout.SOUTH);

        scrollPane = new JScrollPane(historyWrapper);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setBackground(theme.CalculatorTheme.historyBackground);

        JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
        verticalBar.setUI(new CustomScrollBar(theme.CalculatorTheme.historyHover));
        verticalBar.setPreferredSize(new Dimension(6, 0));
        verticalBar.setUnitIncrement(30);
        verticalBar.setBlockIncrement(80);

        SwingUtilities.invokeLater(() -> {
            verticalBar.setValue(verticalBar.getMaximum());
        });
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        setPreferredSize(new Dimension(0, 100));
    }

    private JPanel createEntry(String expression, String result) {
        JPanel entryPanel = new JPanel(new BorderLayout());
        entryPanel.setOpaque(true);
        entryPanel.setBackground(theme.CalculatorTheme.historyBackground);
        entryPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));

        JLabel leftLabel = new JLabel(expression);
        JLabel centerLabel = new JLabel("=");
        JLabel rightLabel = new JLabel(result);

        styleLabel(leftLabel);
        styleLabel(centerLabel);
        styleLabel(rightLabel);

        leftLabel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 0));

        centerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        centerLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        rightLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        rightLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 15));

        entryPanel.add(leftLabel, BorderLayout.WEST);
        entryPanel.add(centerLabel, BorderLayout.CENTER);
        entryPanel.add(rightLabel, BorderLayout.EAST);

        addHoverEffect(entryPanel);

        return entryPanel;
    }

    private void styleLabel(JLabel label) {
        label.setForeground(Color.WHITE);
        label.setFont(HISTORY_FONT);
    }

    private void addHoverEffect(JPanel panel) {
        Color normalColor = theme.CalculatorTheme.historyBackground;
        Color hoverColor = theme.CalculatorTheme.historyHover;

        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panel.setBackground(normalColor);
            }
        });
    }

    public void addEntry(String expression, String result) {
        history.add(createEntry(expression, result), history.getComponentCount() - 1);
        history.revalidate();
        history.repaint();
    }

    @Override
    public void addNotify() {
        super.addNotify();

        SwingUtilities.invokeLater(() -> {
            JScrollBar scrollBar = scrollPane.getVerticalScrollBar();
            scrollBar.setValue(scrollBar.getMaximum());
        });
    }
}