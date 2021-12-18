package ru.cft.focusstart.task6.client.model;

public interface ModelClient {
    int convertInIntPort(String port);
    void checkParamConnection(String host, int port);
    void tryConnectionRequest(String host, int port);
    void sendMessageToServer();
}
