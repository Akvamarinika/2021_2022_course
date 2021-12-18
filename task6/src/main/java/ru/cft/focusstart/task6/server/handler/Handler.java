package ru.cft.focusstart.task6.server.handler;

public interface Handler {
    boolean checkNicknameForRepeat(String nickname);
    void sendMessageToAllClients(String message);
    void sendMessage(String message);
    void read(String message);
}
