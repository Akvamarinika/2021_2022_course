package ru.cft.focusstart.task5.task1.msg;

public enum MsgErrors {
    MSG_ERR_SIZE_TABLE("Размер таблицы может быть от 1 до 32. Пожалуйста, введите число в данном диапазоне."),
    MSG_ERR_TYPE("Неверно введен размер таблицы. Пожалуйста, введите целое число.");

    String msg;

    MsgErrors(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}
