package ru.cft.focusstart.task6.server.dao;

import ru.cft.focusstart.task6.common.dto.Message;

import java.util.List;

public interface Repository {
    void writeNewNickname(String nickname);
    void writeNewMessage(Message message);
    boolean removeNickname(String nickname);
    List<Message> readMessages();
    List<String> readNicknames();
}
