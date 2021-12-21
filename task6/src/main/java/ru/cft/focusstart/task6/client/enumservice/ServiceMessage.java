package ru.cft.focusstart.task6.client.enumservice;

public enum ServiceMessage {
    CONNECTION_MSG("Вы подключились к серверу.\n"),
    NICKNAME_ACCEPTED("Ваш никнейм принят!\n"),
    NEW_USER_ADDED("Новый пользователь %s присоединился к чату.\n"),
    USER_DISCONNECTED("Пользователь %s покинул чат.\n")
    ;

    private final String serviceMsg;

    ServiceMessage(String serviceMsg) {
        this.serviceMsg = serviceMsg;
    }


    @Override
    public String toString() {
        return "SERVER: " + serviceMsg;
    }
}
