package ru.cft.focusstart.task6.client.controller;

import ru.cft.focusstart.task6.client.dto.ConnectionOptions;

public interface ListenerView {
    void informAboutIntentionToConnect(String host, String port);
    void informAboutEnteredNickname(String nickname);
}
