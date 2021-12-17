package ru.cft.focusstart.task6.client;

import ru.cft.focusstart.task6.client.controller.ClientController;
import ru.cft.focusstart.task6.client.model.ModelClient;
import ru.cft.focusstart.task6.client.model.ChatClient;
import ru.cft.focusstart.task6.client.view.ConnectionWindow;
import ru.cft.focusstart.task6.client.view.MainView;
import ru.cft.focusstart.task6.client.view.NickNameWindow;
import ru.cft.focusstart.task6.client.view.View;


public class Client {
    public static void main(String[] args) {
        ModelClient modelClient = new ChatClient();
        ClientController controller = new ClientController();

        View view = new MainView();
        view.subscribe(controller);

        ConnectionWindow connectionWindow = new ConnectionWindow(controller::informAboutIntentionToConnect);

        NickNameWindow nickNameWindow = new NickNameWindow(controller::informAboutEnteredNickname);


        //connectionWindow.setConnectAction(e -> controller.informAboutIntentionToConnect("", ""));

        //ChatWindow chatWindow = new ChatWindow();


    }
}
