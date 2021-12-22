package ru.cft.focusstart.task6.client;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.task6.client.enumservice.ServiceMessage;
import ru.cft.focusstart.task6.client.enumservice.TextDialogWindow;
import ru.cft.focusstart.task6.common.Connection;
import ru.cft.focusstart.task6.common.MessageType;
import ru.cft.focusstart.task6.common.dto.Message;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class Client {
    private Connection connection;
    private static ModelConnectedUsers model;
    private static ViewClient viewClient;
    private volatile boolean isConnect = false;
    private static final String DATE_TIME_PATTERN = "dd.MM.yyyy HH:mm:ss";
    private String yourNickname;

    public boolean isConnect() {
        return isConnect;
    }

    public void setConnect(boolean connect) {
        isConnect = connect;
    }

    public void connectToServer() {
        if (!isConnect) {
            while (true) {
                try {
                    String addressServer = viewClient.openHostIPDialogWindow();
                    int port = viewClient.openPortDialogWindow();
                    log.info("Получен порт: {}", port);

                    Socket socket = new Socket(addressServer, port);
                    connection = new Connection(socket);
                    isConnect = true;
                    viewClient.addMessage(ServiceMessage.CONNECTION_MSG.toString());
                    break;
                } catch (IOException ex) {
                    log.error("Ошибка при подкючении Клиента: введен неверный адрес или порт");
                    viewClient.errorDialogWindow(TextDialogWindow.HOST_PORT_INPUT_ERROR.toString());
                    break;
                }
            }
        } else viewClient.errorDialogWindow(TextDialogWindow.WHEN_ALREADY_CONNECTED_ERROR.toString());
    }

    //регистрация со стороны клиента
    public void nicknameRegistration() {
        while (true) {
            try {
                Message message = connection.receiveMessage();
                log.info("Получено сообщение от сервера, тип: {}", message.getType());

                if (message.getType() == MessageType.REQUEST_NICKNAME) {
                    String nickname = viewClient.getNameUser();
                    yourNickname = nickname;
                    connection.sendMessage(new Message(MessageType.USER_NICKNAME, nickname));
                }

                if (message.getType() == MessageType.NICKNAME_ACCEPTED) {
                    log.info("Был зарегистрирован никнейм в чате");
                    viewClient.addMessage(ServiceMessage.NICKNAME_ACCEPTED.toString());
                    model.setConnectedUsers(message.getConnectionUsers());
                    break;
                }

                if (message.getType() == MessageType.USED_NICKNAME) {
                    log.info("Введен никнейм {}, который уже есть в чате", message.getType());
                    viewClient.errorDialogWindow(TextDialogWindow.USED_NICKNAME_ERROR.toString());
                }

            } catch (IOException | ClassNotFoundException ex) {
                log.warn("Ошибка при регистрации ника, {}", ex.getMessage());
                viewClient.errorDialogWindow(TextDialogWindow.REGISTRATION_ERROR.toString());
                try {
                    connection.close();
                    isConnect = false;
                    break;
                } catch (IOException e) {
                    log.error("Не удалось закрыть соединение, {}", e.getMessage());
                    viewClient.errorDialogWindow(TextDialogWindow.CLOSE_CONNECTION_ERROR.toString());
                }
            }

        }
    }

    public void sendMessageOnServerForOtherUsers(String text) {
        try {
            String dateTime = createDateTimeNowFormatted();
            String textWithDateTimeStamp = String.format("%s %s", dateTime, text);
            connection.sendMessage(new Message(MessageType.TEXT_MESSAGE, textWithDateTimeStamp));
        } catch (IOException ex) {
            viewClient.errorDialogWindow(TextDialogWindow.SEND_MSG_ERROR.toString());
        }
    }

    private String createDateTimeNowFormatted(){
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        log.debug("Format DateTime: {}", dateFormatter.format(dateTime));
        return " [" + dateFormatter.format(dateTime) + "]: ";
    }


    public void receiveMessageFromServer() {
        while (isConnect) {
            try {
                Message message = connection.receiveMessage();

                if (message.getType() == MessageType.TEXT_MESSAGE) {
                    log.info("Получено сообщение от зарегистрированного пользователя");
                    viewClient.addMessage(message.getText());
                }

                if (message.getType() == MessageType.ADDED_USER) {
                    model.addUser(message.getText());
                    viewClient.updateConnectedUsers(model.getConnectedUsers());
                    viewClient.addMessage(String.format(ServiceMessage.NEW_USER_ADDED.toString(), message.getText()));
                    log.info("В чат вошел новый пользователь {}", message.getText());
                }

                if (message.getType() == MessageType.REMOVED_USER) {
                    model.removeUser(message.getText());
                    viewClient.updateConnectedUsers(model.getConnectedUsers());
                    viewClient.addMessage(String.format(ServiceMessage.USER_DISCONNECTED.toString(), message.getText()));
                    log.info("Из чата вышел пользователь {}", message.getText());
                }
            } catch (IOException | ClassNotFoundException ex) {
                log.warn("Пользователь не смог принять сообщение от сервера");
                viewClient.errorDialogWindow(TextDialogWindow.RECEIVE_MSG_ERROR.toString());
                setConnect(false);
                viewClient.updateConnectedUsers(model.getConnectedUsers());
                break;
            }
        }
    }

    public void disableClient() {
        try {
            if (isConnect) {
                connection.sendMessage(new Message(MessageType.DISABLE_USER));
                model.getConnectedUsers().clear();
                isConnect = false;
                viewClient.updateConnectedUsers(model.getConnectedUsers());
            } else viewClient.errorDialogWindow(TextDialogWindow.WHEN_ALREADY_DISCONNECTED_ERROR.toString());
        } catch (IOException ex) {
            log.error("Ошибка при отключении от сервера, {}", ex.getMessage());
            viewClient.errorDialogWindow(TextDialogWindow.DISCONNECTED_ERROR.toString());
        }
    }

    public static void setModel(ModelConnectedUsers model) {
        Client.model = model;
    }

    public static void setViewClient(ViewClient viewClient) {
        Client.viewClient = viewClient;
    }

    public String getYourNickname() {
        return yourNickname;
    }
}
