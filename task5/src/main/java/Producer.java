import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Producer implements Runnable{
    private int producerTime;
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
            log.info("Поток Producer {} был прерван", Thread.currentThread());
        }
    }
}
