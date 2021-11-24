import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {
        ThreadProperty.loadProperties();
        int storageSize = ThreadProperty.getProperty("storage.size");
        Storage storage = new Storage(storageSize);
        createAndStartProducers(storage);
        createAndStartConsumers(storage);


    }

    public static void createAndStartProducers(Storage storage){
        int producerCount = ThreadProperty.getProperty("producer.count");
        int producerTime = ThreadProperty.getProperty("producer.time");
        System.out.println(producerCount);

        for (int id = 1; id <= producerCount; id++) {
            Thread producer = new Thread(new Producer(producerTime, storage));
            producer.setName(("Producer " + id));
            producer.start();
        }
    }

    public static void createAndStartConsumers(Storage storage){
        int consumerCount = ThreadProperty.getProperty("consumer.count");
        int consumerTime = ThreadProperty.getProperty("consumer.time");
        System.out.println(consumerCount);

        for (int id = 1; id <= consumerCount; id++) {
            Thread consumer = new Thread(new Consumer(consumerTime, storage));
            consumer.setName(("Consumer " + id));
            consumer.start();
        }
    }
}
