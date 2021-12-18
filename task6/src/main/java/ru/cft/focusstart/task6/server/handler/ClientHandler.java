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
    public boolean checkNicknameForRepeat(String nickName) {
        return false;
    }

    @Override
    public void sendMessageToAllClients(String message) {
        for (Socket clientSocket : clientsSockets) {
            sendMessage(message);
        }
    }

    @Override
    public void sendMessage(String message) {

    }

    @Override
    public void read(String message) {

    }
}
