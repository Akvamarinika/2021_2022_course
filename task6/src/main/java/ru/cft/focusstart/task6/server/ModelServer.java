package ru.cft.focusstart.task6.server;

public interface ModelServer {
    void nameRequest();
    void getNameFromClient(String name);
    void chatMessagesResponse();
}
