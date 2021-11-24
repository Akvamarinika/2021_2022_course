import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Consumer implements Runnable{
    private int consumerTime;
    private final Storage storage;

    public Consumer(int consumerTime, Storage storage) {
        this.consumerTime = consumerTime;
        this.storage = storage;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(consumerTime);
                storage.buyResource();
            }
        } catch (InterruptedException ex) {
            log.info("Поток Consumer {} был прерван", Thread.currentThread());
        }
    }
}
