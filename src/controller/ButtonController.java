package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ui.DisplayPanel;

public class ButtonController implements ActionListener {
    private DisplayPanel display;

    public ButtonController(DisplayPanel display){
        this.display = display;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String value = e.getActionCommand();
        display.appendText(value);
    }
}
