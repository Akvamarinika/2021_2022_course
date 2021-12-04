import lombok.extern.slf4j.Slf4j;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class Storage {
    private int storageSize;
    private final Set<Integer> resources = new HashSet<>(storageSize);
    private volatile int idRes = 1;

    public Storage(int storageSize) {
        this.storageSize = storageSize;
    }

    public synchronized void buyResource() throws InterruptedException {
        while (resources.size() == 0){
            log.info("Перешел в режим ожидания...");

            this.wait();
            log.info("Возобновил работу.");
        }

        int idResource = resources.iterator().next();
        resources.remove(idResource);
        log.info("Купил ресурс id=={}. Остаток на складе: {}", idResource, resources.size());
        this.notify();

    }

    public synchronized void makeResource() throws InterruptedException {
        while (resources.size() >= storageSize){
            log.info("Перешел в режим ожидания...");
            this.wait();
            log.info("Возобновил работу.");
        }

        resources.add(idRes);
        log.info("Произвел ресурс id=={}. В наличии на складе: {}", idRes, resources.size());
        idRes++;
        this.notify();
    }
}
