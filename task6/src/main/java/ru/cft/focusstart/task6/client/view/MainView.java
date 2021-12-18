package ru.cft.focusstart.task6.client.view;

import ru.cft.focusstart.task6.client.controller.ListenerView;

import java.util.ArrayList;
import java.util.List;

public class MainView implements ViewSigner, ListenerModel{
    private final List<ListenerView> listeners = new ArrayList<>();
    private Window window;

//    private ConnectionWindow connectionWindow;
//    private NickNameWindow nickNameWindow;
//    private ChatWindow chatWindow;

    public MainView() {
        notifyAboutPressConnectionBtn();

        if (window != null){
            window.visible();
        }
    }


//    public void setConnectionWindow(ConnectionWindow connectionWindow) {
//        this.connectionWindow = connectionWindow;
//    }
//
//    public void setNickNameWindow(NickNameWindow nickNameWindow) {
//        this.nickNameWindow = nickNameWindow;
//    }
//
//    public void setChatWindow(ChatWindow chatWindow) {
//        this.chatWindow = chatWindow;
//    }

    public void subscribe(ListenerView listener){
        listeners.add(listener);
    }

    public void notifyAboutPressConnectionBtn(){
        window = new ConnectionWindow((host, port) -> {

            if(!host.isEmpty() && !port.isEmpty()){
                listeners.forEach(listener -> listener.informAboutIntentionToConnect(host, port));
            }

        });
    }

    public void notifyAboutEnteredNickname(){
        window = new NickNameWindow(nickName -> {

            if(!nickName.isEmpty()){
                listeners.forEach(listener -> listener.informAboutEnteredNickname(nickName));
            }

        });
    }

    @Override
    public void informAboutClientConnected() {
        notifyAboutEnteredNickname();

        if (window != null){
            window.visible();
        }
    }

    @Override
    public void informThatNicknameIsAccepted() {

    }

    @Override
    public void informAboutGetNewMessage() {

    }
}
