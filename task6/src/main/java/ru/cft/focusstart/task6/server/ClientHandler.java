package ru.cft.focusstart.task6.server;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.task6.common.Connection;
import ru.cft.focusstart.task6.common.MessageType;
import ru.cft.focusstart.task6.common.dto.Message;

import java.io.IOException;
import java.net.Socket;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
public class ClientHandler extends Thread {
    private final Socket socket;
    private static ModelMapUsers modelMapUsers;
    private static Server server;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    //регистрация нового участника чата
    private String requestNicknameAndAddingUser(Connection connection) {
        while (true) {
            try {
                connection.sendMessage(new Message(MessageType.REQUEST_NICKNAME));
                log.info("Server отправил запрос на nickname");

                Message responseMessage = connection.receiveMessage();
                String nickname = responseMessage.getText();
                log.info("Server получил nickname: {}", nickname);

                if (responseMessage.getType() == MessageType.USER_NICKNAME && nickname != null &&
                        !nickname.isEmpty() && !modelMapUsers.getAllConnectedUsers().containsKey(nickname)) {
                    modelMapUsers.addUser(nickname, connection);
                    log.info("Участник nickname: {} был добавлен в список зарегистрированных в чате", nickname);

                    Set<String> registeredNickname = new HashSet<>();
                    for (Map.Entry<String, Connection> users : modelMapUsers.getAllConnectedUsers().entrySet()) {
                        registeredNickname.add(users.getKey());
                    }

                    connection.sendMessage(new Message(MessageType.NICKNAME_ACCEPTED, registeredNickname));
                    log.info("Был отправлен список участников чата, nickname's: {}", nickname);

                    server.sendMessageAllUsers(new Message(MessageType.ADDED_USER, nickname));
                    log.info("Новый участник присоеденился к чату: {}", nickname);
                    return nickname;
                } else {
                    connection.sendMessage(new Message(MessageType.USED_NICKNAME));
                    log.info("Введен занятый nickname: {}", nickname);
                }
            } catch (IOException | ClassNotFoundException ex) {
                log.warn("Ошибка при запросе / добавлении нового участника чата");
            }
        }
    }

    private void sendingMessagesToUsers(Connection connection, String nickname) {
        while (true) {
            try {
                Message message = connection.receiveMessage();
                log.info("Server получил новое сообщение от nickname: {}", nickname);

                if (message.getType() == MessageType.TEXT_MESSAGE) {
                    String textMessage = String.format("%s %s\n", nickname, message.getText());
                    server.sendMessageAllUsers(new Message(MessageType.TEXT_MESSAGE, textMessage));
                }

                if (message.getType() == MessageType.DISABLE_USER) {
                    server.sendMessageAllUsers(new Message(MessageType.REMOVED_USER, nickname));
                    modelMapUsers.removeUser(nickname);
                    connection.close();
                    log.info("Участник {} покинул чат", nickname);
                    break;
                }
            } catch (Exception e) {
                log.info("Server не смог разослать сообщение участника {}, либо он покинул чат", nickname);
                break;
            }
        }
    }

    @Override
    public void run() {
        log.info("Подключился к серверу участник с адресом {}, порт {}", socket.getInetAddress(), socket.getPort());

        try {
            Connection connection = new Connection(socket);
            String nameUser = requestNicknameAndAddingUser(connection);
            sendingMessagesToUsers(connection, nameUser);
        } catch (Exception e) {
            log.info("Server не смог разослать сообщение участника");
        }
    }

    public static void setModelMapUsers(ModelMapUsers modelMapUsers) {
        ClientHandler.modelMapUsers = modelMapUsers;
    }

    public static void setServer(Server server) {
        ClientHandler.server = server;
    }
}

