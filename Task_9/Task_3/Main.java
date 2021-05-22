package Task_3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) {
        BlockingQueue blockingQueue = new LinkedBlockingQueue<>();
        Producer producer = new Producer(blockingQueue);
        Consumer consumer = new Consumer(blockingQueue);
        producer.start();
        consumer.start();
    }
}
