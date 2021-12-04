package ru.cft.focusstart.task5.task1;

import ru.cft.focusstart.task5.task1.msg.MsgErrors;

import java.util.Scanner;

public class Application {
    public static final String INPUT_TEXT = "Введите размер таблицы: ";

    public static void main(String[] args) {
        int tableSize = keyboardInput();
        String table = createTable(tableSize);
        System.out.println(table);
    }

    public static int keyboardInput(){
        int input;
        try (Scanner scanner = new Scanner(System.in)){
            System.out.println(INPUT_TEXT);

            if (scanner.hasNextInt()) {
                input = scanner.nextInt() ;
            } else {
                System.err.println(MsgErrors.MSG_ERR_TYPE);
                input = keyboardInput();
            }

        }
        return input;
    }

    public static String createTable(int tableSize) {
        if (InputValidator.checkTableSize(tableSize)) {
            TableMulti tableMulti = new TableMulti(tableSize);
            return tableMulti.generateTable();
        }
        return "";
    }
}
