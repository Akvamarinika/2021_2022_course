package ru.cft.focusstart.task6.client;

import java.util.HashSet;
import java.util.Set;

public class ModelConnectedUsers {
    private Set<String> connectedUsers = new HashSet<>();

    protected Set<String> getConnectedUsers() {
        return connectedUsers;
    }

    protected void addUser(String nameUser) {
        connectedUsers.add(nameUser);
    }

    protected void removeUser(String nameUser) {
        connectedUsers.remove(nameUser);
    }

    protected void setConnectedUsers(Set<String> connectedUsers) {
        this.connectedUsers = connectedUsers;
    }
}
