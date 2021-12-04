import exeption.PropertyException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static final String PROPERTY_NAME_STORAGE_SIZE = "storage.size";
    public static final String PROPERTY_NAME_PRODUCER_COUNT = "producer.count";
    public static final String PROPERTY_NAME_PRODUCER_TIME = "producer.time";
    public static final String PROPERTY_NAME_CONSUMER_COUNT = "consumer.count";
    public static final String PROPERTY_NAME_CONSUMER_TIME = "consumer.time";

    public static void main(String[] args) {
        try {
            ThreadProperty.loadProperties();
            int storageSize = ThreadProperty.getProperty(PROPERTY_NAME_STORAGE_SIZE);
            Storage storage = new Storage(storageSize);
            createAndStartProducers(storage);
            createAndStartConsumers(storage);
        } catch (PropertyException ex) {
            System.out.println(ex.getMessage() + ex.getPropertyName());
        } catch (Exception ex){
            log.error("Неизвестная ошибка в процессе выполнения программы: ", ex.getMessage());
        }
    }

    public static void createAndStartProducers(Storage storage){
        int producerCount = ThreadProperty.getProperty(PROPERTY_NAME_PRODUCER_COUNT);
        int producerTime = ThreadProperty.getProperty(PROPERTY_NAME_PRODUCER_TIME);

        for (int id = 1; id <= producerCount; id++) {
            Thread producer = new Thread(new Producer(producerTime, storage));
            producer.setName(("Producer " + id));
            producer.start();
        }
    }

    public static void createAndStartConsumers(Storage storage){
        int consumerCount = ThreadProperty.getProperty(PROPERTY_NAME_CONSUMER_COUNT);
        int consumerTime = ThreadProperty.getProperty(PROPERTY_NAME_CONSUMER_TIME);

        for (int id = 1; id <= consumerCount; id++) {
            Thread consumer = new Thread(new Consumer(consumerTime, storage));
            consumer.setName(("Consumer " + id));
            consumer.start();
        }
    }
}
