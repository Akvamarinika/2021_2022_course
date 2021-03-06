package ru.cft.focusstart.task6.client;

import javax.swing.*;

public enum ImageChat {
    ICON_APP("icon.png"),
    ICON_FALSE("false.png"),
    ICON_TRUE("true.png"),
    ;

    private final String fileName;
    private ImageIcon imageIcon;

    ImageChat(String fileName) {
        this.fileName = fileName;
    }

    public synchronized ImageIcon getImageIcon() {
        if (imageIcon == null) {
            imageIcon = new ImageIcon(ClassLoader.getSystemResource(fileName));
        }

        return imageIcon;
    }
}
