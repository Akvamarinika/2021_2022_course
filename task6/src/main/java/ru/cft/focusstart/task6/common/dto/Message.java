package ru.cft.focusstart.task6.common.dto;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.task6.common.MessageType;
import java.io.Serializable;
import java.util.Set;

@Slf4j
public class Message implements Serializable {
    private final MessageType type;
    private final String text;
    private final Set<String> connectionUsers;

    public Message(MessageType type, String text) {
        this.text = text;
        this.type = type;
        this.connectionUsers = null;
    }

    public Message(MessageType type, Set<String> connectionUsers) {
        this.type = type;
        this.text = null;
        this.connectionUsers = connectionUsers;
    }

    public Message(MessageType type) {
        this.type = type;
        this.text = null;
        this.connectionUsers = null;
    }

    public MessageType getType() {
        return type;
    }

    public Set<String> getConnectionUsers() {
        return connectionUsers;
    }

    public String getText() {
        return text;
    }



}
