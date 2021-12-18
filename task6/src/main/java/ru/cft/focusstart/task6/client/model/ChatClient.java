package ru.cft.focusstart.task6.client.model;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.task6.client.exception.ConnectionException;
import ru.cft.focusstart.task6.client.view.ListenerModel;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

@Slf4j
public class ChatClient implements ModelClient, ModelSigner {
    public static final String CHARSET = "UTF-8";
    private Socket socket;
    private String host;
    private int port;

    @Override
    public int convertInIntPort(String port) {
        try {
            return Integer.parseInt(port);
        } catch (NumberFormatException ex) {
            log.error("Не удалось преобразовать порт к числу");
            throw new ConnectionException("Неверно введен порт!");
        }
    }

    @Override
    public void checkParamConnection(String host, int port) {

    }

    @Override
    public void tryConnectionRequest(String host, int port){
        try {
            socket = new Socket(host, port);
            if(socket.isConnected()){
                log.info("Клиент подключился");
            }

        } catch (IOException e) {
            log.info("Не удалось подключиться к серверу. Хост:{} Порт:{}", host, port);
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

    @Override
    public void subscribe(ListenerModel listener) {

    }
}
