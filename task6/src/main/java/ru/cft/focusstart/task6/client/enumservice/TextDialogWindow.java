package ru.cft.focusstart.task6.client.enumservice;

public enum TextDialogWindow {
    WHEN_ALREADY_CONNECTED_ERROR("Вы уже подключены к серверу!"),
    HOST_PORT_INPUT_ERROR("Ошибка при подкючении. Возможно, Вы ввели не верный хост или порт. Попробуйте еще раз"),
    USED_NICKNAME_ERROR("Данный никнейм уже занят. Пожалуйста, введите другой ник"),
    REGISTRATION_ERROR("Ошибка при регистрации ника. Попробуйте переподключиться"),
    CLOSE_CONNECTION_ERROR("Произошла ошибка при закрытии соединения"),
    SEND_MSG_ERROR("Ошибка при отправке сообщения на сервер"),
    RECEIVE_MSG_ERROR("Ошибка при приеме сообщения от сервера."),
    WHEN_ALREADY_DISCONNECTED_ERROR("Вы уже отключены!"),
    DISCONNECTED_ERROR("Произошла ошибка при отключении от чата."),
    NO_CONNECTED_ERROR("Вы не подключены к чату. Нажмите кнопку Подключиться")
    ;

    private final String dialogMsg;

    TextDialogWindow(String dialogMsg) {
        this.dialogMsg = dialogMsg;
    }

    @Override
    public String toString() {
        return dialogMsg;
    }
}
