package Task_3;

import java.util.concurrent.BlockingQueue;

public class Producer extends Thread {

    private final BlockingQueue blockingDeque;

    private final Object object;

    public Producer(BlockingQueue blockingDeque, Object object) {
        this.blockingDeque = blockingDeque;
        this.object = object;
    }

    @Override
    public void run() {
        while (true) {
            int element = (int) (Math.random() * 100);
            try {
                int timeForSleep = (int) (Math.random() * 500);
                blockingDeque.put(element);
                System.out.print(" Producer ");
                System.out.print(element);
                Thread.sleep(timeForSleep);
                if (blockingDeque.size() >= 10) {
                    synchronized (object) {
                        object.wait();
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Неудачно");
            }

        }

    }
}
