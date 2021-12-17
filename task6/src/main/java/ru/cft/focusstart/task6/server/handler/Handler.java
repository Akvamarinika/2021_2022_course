package ru.cft.focusstart.task6.server.handler;

public interface Handler {
    boolean checkNicknameForRepeat();
    void sendMessageToAllClients(String message);
    void sendMsg(String message);
    void read(String message);
}
