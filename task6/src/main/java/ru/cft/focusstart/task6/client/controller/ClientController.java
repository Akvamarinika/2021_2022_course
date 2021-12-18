package ru.cft.focusstart.task6.client.controller;

import ru.cft.focusstart.task6.client.dto.ConnectionOptions;
import ru.cft.focusstart.task6.client.model.ModelClient;

public class ClientController implements Controller, ListenerView {
    private ModelClient modelClient;

    @Override
    public void informAboutIntentionToConnect(String host, String port) {
        int portInt = modelClient.convertInIntPort(port);
        //modelClient.checkParamConnection();
        modelClient.tryConnectionRequest(host, portInt);
    }

    @Override
    public void informAboutEnteredNickname(String nickname) {

    }

    @Override
    public void setModelClient(ModelClient modelClient) {
        this.modelClient = modelClient;
    }
}
