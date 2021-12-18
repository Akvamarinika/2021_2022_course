package ru.cft.focusstart.task6.server.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.cft.focusstart.task6.common.dto.Message;
import java.util.List;

public class JsonDB implements Repository{
    private final ClassLoader classLoader = getClass().getClassLoader();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String FILE_NAME_NICKNAME = "nicknames.json";
    private static final String FILE_NAME_MESSAGES = "messages.json";

    @Override
    public void writeNewNickname(String nickname) {

    }

    @Override
    public void writeNewMessage(Message message) {

    }

    @Override
    public boolean removeNickname(String nickname) {
        return false;
    }

    @Override
    public List<Message> readMessages() {
        return null;
    }

    @Override
    public List<String> readNicknames() {
        return null;
    }
}
