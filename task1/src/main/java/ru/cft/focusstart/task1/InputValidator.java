package ru.cft.focusstart.task1;

public class InputValidator {
    private static final String MSG_ERR_SIZE_TABLE = "Размер таблицы может быть от 1 до 32. Пожалуйста, введите число в данном диапазоне.";
    public static final String MSG_ERR_TYPE = "Неверно введен размер таблицы. Пожалуйста, введите целое число.";

    public static boolean countArgs(String[] args){
        if (args.length == 1){
            return true;
        }

        System.err.println(MSG_ERR_TYPE);
        return false;
    }

    public static boolean isInteger(String input){
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {

            System.err.println(MSG_ERR_TYPE);
            return false;
        }
    }

    public static boolean checkTableSize(int size) throws IllegalArgumentException{
        if (size >= 1 && size <= 32){
            return true;
        }

        throw new IllegalArgumentException(MSG_ERR_SIZE_TABLE);
    }
}
