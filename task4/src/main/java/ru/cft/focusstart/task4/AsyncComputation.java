package ru.cft.focusstart.task4;

import lombok.extern.slf4j.Slf4j;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

@Slf4j
public class AsyncComputation {
    public static final int CORES_AMOUNT = Runtime.getRuntime().availableProcessors();
    private final int input;

    public AsyncComputation(int input) {
        this.input = input;
    }

    public double calculateAsync() throws InterruptedException, ExecutionException {
        int subrangeSize = calcSubrangeSize(input);

        if (subrangeSize == -1){
            return 0;
        }

        double mainResult = 0.;
        ExecutorService executorService = Executors.newFixedThreadPool(CORES_AMOUNT);
        Set<Task> tasks = createSetWithTasks(subrangeSize);
        List<Future<Double>> futureResults = executorService.invokeAll(tasks);

        for(Future<Double> result : futureResults){
            mainResult += result.get();
        }

        log.info("Получен общий результат по всем потокам {}", mainResult);
        executorService.shutdown();
        return mainResult;

    }

    private int calcSubrangeSize(int input) {
        return input > 0 ? input / CORES_AMOUNT : -1;
    }

    private Set<Task> createSetWithTasks(int subrangeSize){
        int currentStart = 0;
        int currentEnd = 0;
        int remainder = input - subrangeSize * CORES_AMOUNT;
        Set<Task> tasks = new HashSet<>(CORES_AMOUNT);

        for (int i = 0; i < CORES_AMOUNT; i++) {
            if (i == CORES_AMOUNT - 1) {
                currentEnd += remainder;
            }

            currentEnd += subrangeSize;
            tasks.add(new Task(currentStart, currentEnd));
            currentStart += subrangeSize;
        }

        return tasks;
    }
}
