package ru.cft.focusstart.task6.client;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainClient {
    public static void main(String[] args) {
        try {
            Client client = new Client();
            Client.setModel(new ModelConnectedUsers());
            ViewClient viewClient = new ViewClient(client);
            Client.setViewClient(viewClient);
            viewClient.initFrameClient();

            while (true) {
                if (client.isConnect()) {
                    client.nicknameRegistration();
                    client.receiveMessageFromServer();
                    client.setConnect(false);
                }
            }
        } catch (Exception e) {
            log.error("Неизвестная ошибка в процессе выполнения программы Client: {}", e.getMessage());
        }
    }
}
