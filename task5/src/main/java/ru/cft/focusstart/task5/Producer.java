package ru.cft.focusstart.task5;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Producer implements Runnable{
    private final int producerTime;
    private final Storage storage;

    public Producer(int producerTime, Storage storage) {
        this.producerTime = producerTime;
        this.storage = storage;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(producerTime);
                storage.makeResource();
            }

        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
