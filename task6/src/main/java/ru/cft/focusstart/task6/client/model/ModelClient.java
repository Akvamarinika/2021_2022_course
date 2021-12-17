package ru.cft.focusstart.task6.client.model;

public interface ModelClient {
    void connectionRequest(String host, int port);
    void sendMessageToServer();
}
