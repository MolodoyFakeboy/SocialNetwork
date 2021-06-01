package Task_3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) {
        BlockingQueue blockingQueue = new LinkedBlockingQueue<>();
        final Object monitor = new Object();
        Producer producer = new Producer(blockingQueue,monitor);
        Consumer consumer = new Consumer(blockingQueue,monitor);
        producer.start();
        consumer.start();
    }
}
