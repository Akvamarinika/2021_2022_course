package ru.cft.focusstart.task4;

import java.util.Scanner;

public class Main {
    public static final String INPUT_TEXT = "Введите число: ";
    public static final String MSG_ERR_TYPE ="Не является целым числом. Пожалуйста, введите целое число.";

    public static void main(String[] args) {
        int inputNum = keyboardInput();
        AsyncComputation asyncComputation = new AsyncComputation(inputNum);
        System.out.println(asyncComputation.calculateAsync());

    }

    public static int keyboardInput(){
        int input;
        try (Scanner scanner = new Scanner(System.in)){
            System.out.println(INPUT_TEXT);

            if (scanner.hasNextInt()) {
                input = scanner.nextInt() ;
            } else {
                System.err.println(MSG_ERR_TYPE);
                input = keyboardInput();
            }

        }
        return input;
    }
}
