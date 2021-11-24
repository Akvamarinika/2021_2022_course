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

    public void buyResource(){
        synchronized (resources) {
            while (resources.size() == 0){
                try {
                    log.info("Перешел в режим ожидания...");
                    resources.wait();
                    log.info("Возобновил работу.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
            int idResource = resources.iterator().next();
            resources.remove(idResource);
            log.info("Купил ресурс id=={}. Остаток на складе: {}", idResource, resources.size());
            resources.notify();
        }
    }

    public  void makeResource(){
        synchronized (resources) {
            while (resources.size() >= storageSize){
                try {
                    log.info("Перешел в режим ожидания...");
                    resources.wait();
                    log.info("Возобновил работу.");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
                resources.add(idRes);
                log.info("Произвел ресурс id=={}. В наличии на складе: {}", idRes, resources.size());
                idRes++;
                resources.notify();
       }
    }
}
