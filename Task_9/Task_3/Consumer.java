package Task_3;

import java.util.concurrent.BlockingQueue;

public class Consumer extends Thread {
    private BlockingQueue blockingDeque;

    public Consumer(BlockingQueue blockingDeque) {
        this.blockingDeque = blockingDeque;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Consumed " + blockingDeque.take());
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
