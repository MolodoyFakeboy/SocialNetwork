package Task_3;

import java.util.concurrent.BlockingQueue;

public class Producer extends Thread {

    private BlockingQueue blockingDeque;

    public Producer(BlockingQueue blockingDeque) {
        this.blockingDeque = blockingDeque;
    }

    @Override
    public void run() {
        while (true) {
            int element = (int) (Math.random() * 100);
            try {
                blockingDeque.put(element);
                System.out.println("Producer " + element);
                Thread.sleep(300);
            } catch (InterruptedException e) {
                System.out.println("Неудачно");
            }
        }

    }
}
