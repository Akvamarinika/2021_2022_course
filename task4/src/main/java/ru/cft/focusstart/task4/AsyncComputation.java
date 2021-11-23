package ru.cft.focusstart.task4;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
@AllArgsConstructor
public class AsyncComputation {
    public static final int CORES_AMOUNT = Runtime.getRuntime().availableProcessors();
    public int input;

    public double calculateAsync(){
        double mainResult = 0.;
        ExecutorService executorService = Executors.newFixedThreadPool(CORES_AMOUNT);
        int currentStart = 0;
        int currentEnd = 0;
        int subrangeSize = calcSubrangeSize(input);
        int remainder = input - subrangeSize * CORES_AMOUNT;

        if (subrangeSize == -1){
            return 0;
        }

        try {
            for (int i = 0; i < CORES_AMOUNT; i++) {
                if (i == CORES_AMOUNT - 1) {
                    currentEnd += remainder;
                }

                currentEnd += subrangeSize;
                Future<Double> result = executorService.submit(new Task(currentStart, currentEnd));
                mainResult += result.get();
                currentStart += subrangeSize;
            }
        } catch (InterruptedException e) {
            log.info("Поток был прерван. {}", e.getMessage());
        } catch (ExecutionException e) {
            log.info("Что-то пошло не так в вычеслении Task. {}", e.getMessage());
        }

        executorService.shutdown();
        log.info("Получен общий результат по всем потокам {}", mainResult);
        return mainResult;

    }

    private int calcSubrangeSize(int input) {
        return input > 0 ? input / CORES_AMOUNT : -1;
    }
}
