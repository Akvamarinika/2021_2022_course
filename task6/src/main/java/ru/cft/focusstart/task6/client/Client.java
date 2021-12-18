package ru.cft.focusstart.task6.client;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.task6.client.controller.ClientController;
import ru.cft.focusstart.task6.client.exception.ConnectionException;
import ru.cft.focusstart.task6.client.model.ChatClient;
import ru.cft.focusstart.task6.client.view.MainView;

@Slf4j
public class Client {
    public static void main(String[] args) {
        try {
            ChatClient chatClient = new ChatClient();

            ClientController controller = new ClientController();
            controller.setModelClient(chatClient);

            MainView view = new MainView();
            view.subscribe(controller);

            chatClient.subscribe(view);
        } catch (ConnectionException e) {
            log.error("Ошибка: {}", e.getMessage());
        }

//        ConnectionWindow connectionWindow = new ConnectionWindow(controller::informAboutIntentionToConnect);
//
//        NickNameWindow nickNameWindow = new NickNameWindow(controller::informAboutEnteredNickname);


        //connectionWindow.setConnectAction(e -> controller.informAboutIntentionToConnect("", ""));

        //ChatWindow chatWindow = new ChatWindow();


    }
}
