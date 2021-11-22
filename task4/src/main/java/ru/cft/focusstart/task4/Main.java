package ru.cft.focusstart.task4;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Main {
    public static final String INPUT_TEXT = "Введите число: ";
    public static final String MSG_ERR_TYPE ="Не является целым числом. Пожалуйста, введите целое число.";
    public static final int COUNT_THREAD = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(COUNT_THREAD);

        System.out.println(COUNT_THREAD);

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
