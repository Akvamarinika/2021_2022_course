package ru.cft.focusstart.task4;

import lombok.extern.slf4j.Slf4j;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

@Slf4j
public class Main {
    public static final String INPUT_TEXT = "Введите число: ";
    public static final String MSG_ERR_TYPE ="Не является целым числом. Пожалуйста, введите целое число.";

    public static void main(String[] args) {
        try {
            int inputNum = keyboardInput();
            AsyncComputation asyncComputation = new AsyncComputation(inputNum);
            System.out.println(asyncComputation.calculateAsync());
        } catch (InterruptedException e) {
            log.info("Поток был прерван. {}", e.getMessage());
        } catch (ExecutionException e) {
            log.info("Что-то пошло не так при вычеслении Task. {}", e.getMessage());
        }
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
