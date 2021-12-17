package ru.cft.focusstart.task6.client.model;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

@Slf4j
public class ChatClient implements ModelClient {
    public static final String CHARSET = "UTF-8";
    private Socket socket;
    private String host;
    private int port;

    @Override
    public void connectionRequest(String host, int port){
        try {
            socket = new Socket(host, port);
            log.info("Клиент подключился");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void sendMessageToServer() {
        try (Scanner scanner = new Scanner(System.in)) {
            if (System.in.available() > 0){
                String data = scanner.next();
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(data.getBytes(CHARSET));
                outputStream.flush();
            }
        } catch (IOException e) {
            log.warn("Не смог отправить сообщение на Сервер");
        }
    }
}
