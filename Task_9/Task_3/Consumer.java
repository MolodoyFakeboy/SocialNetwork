package Task_3;

import java.util.concurrent.BlockingQueue;

public class Consumer extends Thread {
    private final BlockingQueue blockingDeque;

    private final Object object;

    public Consumer(BlockingQueue blockingDeque, Object object) {
        this.blockingDeque = blockingDeque;
        this.object = object;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int timeForSleep = (int) (Math.random() * 1000);
                int necessarySize = (int) (Math.random() * 5);
                Thread.sleep(timeForSleep);
                System.out.println(" Сomsumed " + blockingDeque.take());
                if (blockingDeque.size() <= necessarySize) {
                    synchronized (object) {
                        object.notify();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
