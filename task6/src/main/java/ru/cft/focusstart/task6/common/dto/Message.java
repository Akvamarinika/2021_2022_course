package ru.cft.focusstart.task6.common.dto;

import java.time.LocalDateTime;

public class Message {
    private String nickName;
    private LocalDateTime dateTime;

    public Message(String nickName, LocalDateTime dateTime) {
        this.nickName = nickName;
        this.dateTime = dateTime;
    }
}
