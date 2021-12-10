package ru.cft.focusstart.task4;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

@Slf4j
@AllArgsConstructor
public class Task implements Callable<Double> {
  private final int idxStart;
  private final int idxStop;

    @Override
    public Double call() {
        log.info("Запущен поток {} считающий значения от {} до {}", Thread.currentThread(), idxStart, idxStop);
        double result = 0.;

        for (int i = idxStart; i < idxStop; i++) {
            result += 1 / Math.pow(2, i);
        }

        log.info("Поток {} вычислил результат {}", Thread.currentThread(), result);
        return result;
    }
}
