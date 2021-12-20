package ru.cft.focusstart.task6.server;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.task6.common.Connection;
import ru.cft.focusstart.task6.common.dto.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

@Slf4j
public class Server {
    private ServerSocket serverSocket;
    private static ModelMapUsers modelMapUsers;
    private static int port;
    private static volatile boolean isServerStart = false;

    public Server(int port) {
        Server.port = port;
        log.info("port {}", getPort());
        modelMapUsers = new ModelMapUsers();
        ClientHandler.setModelMapUsers(modelMapUsers);
        ClientHandler.setServer(this);
    }


    public void startServer() {
        try {
            serverSocket = new ServerSocket(port);
            isServerStart = true;
            log.info("Server запущен");

            while (true) {
                if (isServerStart) {
                    this.acceptServer();
                    isServerStart = false;
                }
            }
        } catch (IOException ex) {
            log.error("Ошибка при запуске Server {}", ex.getMessage());
        }
    }

    public void acceptServer() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                log.info("Server ожидает клиентов...");
                new ClientHandler(socket).start();
            } catch (IOException ex) {
                log.error("Связь с Server была разорвана {}", ex.getMessage());
                break;
            }
        }
    }

    public void sendMessageAllUsers(Message message) {
        log.info("Server начал массовую рассылку сообщения. Тип сообщения: {}", message.getType());

        for (Map.Entry<String, Connection> user : modelMapUsers.getAllConnectedUsers().entrySet()) {
            try {
                user.getValue().sendMessage(message);
            } catch (IOException ex) {
                log.info("Server не смог отправить сообщение участникам чата. Тип сообщения {}", message.getType());
            }
        }
    }

    public static int getPort() {
        return port;
    }
}