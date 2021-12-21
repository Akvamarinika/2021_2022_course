package ru.cft.focusstart.task6.server;

import ru.cft.focusstart.task6.common.Connection;
import java.util.HashMap;
import java.util.Map;

public class ModelMapUsers {
    private final Map<String, Connection> allConnectedUsers = new HashMap<>();

    public Map<String, Connection> getAllConnectedUsers() {
        return allConnectedUsers;
    }

    public void addUser(String nickname, Connection connection) {
        allConnectedUsers.put(nickname, connection);
    }

    public void removeUser(String nickname) {
        allConnectedUsers.remove(nickname);
    }

}