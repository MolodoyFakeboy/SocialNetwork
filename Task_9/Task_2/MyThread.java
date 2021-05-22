package Task_2;

public class MyThread extends Thread {

    private Object monitor;

    public MyThread(String name, Object monitor) {
        super(name);
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(" Мое имя : " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (monitor) {
                monitor.notify();
            }
            synchronized (monitor) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
