package ru.cft.focusstart.task6.client;

import lombok.extern.slf4j.Slf4j;
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
                    viewClient.addMessage("SERVER: Вы подключились к серверу.\n");
                    break;
                } catch (IOException ex) {
                    log.error("Ошибка при подкючении Клиента: введен неверный адрес или порт");
                    viewClient.errorDialogWindow("Ошибка при подкючении. Возможно, Вы ввели не верный хост или порт. Попробуйте еще раз");
                    break;
                }
            }
        } else viewClient.errorDialogWindow("Вы уже подключены к серверу!");
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
                    viewClient.addMessage("SERVER: ваш никнейм принят!\n");
                    model.setConnectedUsers(message.getConnectionUsers());
                    break;
                }

                if (message.getType() == MessageType.USED_NICKNAME) {
                    log.info("Введен никнейм {}, который уже есть в чате", message.getType());
                    viewClient.errorDialogWindow("Данный никнейм уже занят. Пожалуйста, введите другой ник");
                }

            } catch (IOException | ClassNotFoundException ex) {
                log.warn("Ошибка при регестрации ника, {}", ex.getMessage());
                viewClient.errorDialogWindow("Ошибка при регестрации ника. Попробуйте переподключиться");
                try {
                    connection.close();
                    isConnect = false;
                    break;
                } catch (IOException e) {
                    log.error("Не удалось закрыть соединение, {}", e.getMessage());
                    viewClient.errorDialogWindow("Произошла ошибка при закрытии соединения");
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
            viewClient.errorDialogWindow("Ошибка при отправке сообщения на сервер");
        }
    }

    private String createDateTimeNowFormatted(){
        LocalDateTime dateTime = LocalDateTime.now();
        String DATE_TIME_PATTERN = "dd.MM.yyyy HH:mm:ss";
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
                    log.info("В чат вошел новый пользователь");
                    model.addUser(message.getText());
                    viewClient.updateConnectedUsers(model.getConnectedUsers());
                    viewClient.addMessage(String.format("SERVER: новый пользователь %s присоединился к чату.\n", message.getText()));
                }

                if (message.getType() == MessageType.REMOVED_USER) {
                    log.info("Из чата вышел пользователь");
                    model.removeUser(message.getText());
                    viewClient.updateConnectedUsers(model.getConnectedUsers());
                    viewClient.addMessage(String.format("SERVER: пользователь %s покинул чат.\n", message.getText()));
                }
            } catch (IOException | ClassNotFoundException ex) {
                log.warn("Пользователь не смог принять сообщение от сервера");
                viewClient.errorDialogWindow("Ошибка при приеме сообщения от сервера.");
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
            } else viewClient.errorDialogWindow("Вы уже отключены!");
        } catch (IOException ex) {
            log.error("Ошибка при отключении от сервера, {}", ex.getMessage());
            viewClient.errorDialogWindow("SERVER: произошла ошибка при отключении.");
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
