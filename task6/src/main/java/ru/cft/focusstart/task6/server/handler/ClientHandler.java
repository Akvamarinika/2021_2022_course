package ru.cft.focusstart.task6.server.handler;

import ru.cft.focusstart.task6.server.handler.Handler;

import java.net.Socket;
import java.util.List;

public class ClientHandler implements Handler {
    private final List<Socket> clientsSockets;

    public ClientHandler(List<Socket> copyClientsSockets) {
        this.clientsSockets = copyClientsSockets;
    }

    @Override
    public boolean checkNicknameForRepeat() {
        return false;
    }

    @Override
    public void sendMessageToAllClients(String message) {

    }

    @Override
    public void sendMsg(String message) {

    }

    @Override
    public void read(String message) {

    }
}
