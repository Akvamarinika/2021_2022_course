package ru.cft.focusstart.task6.client.view;

import javax.swing.*;

public abstract class Window extends JFrame {
    public void visible() {
        setVisible(true);
    }

    public void close() {
        dispose();
    }


}
