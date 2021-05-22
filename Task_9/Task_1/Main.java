package Task_1;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        final Object monitor = new Object();

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, " Рядовой-Поток 1 ");

        System.out.println("Состояние потока до начала работы : " + ProcessState.State(thread.getState()));

        Thread thread1 = new Thread(() -> {
            System.out.println(" Начинаем работу мое состояние " + ProcessState.State(Thread.currentThread().getState()));
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
                try {
                    synchronized (monitor) {
                        monitor.wait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "Поток ожидатель");

        thread1.start();

        thread.start();

        Thread.sleep(200);

        synchronized (monitor) {
            monitor.notify();
            System.out.println(thread1.getName() + " " + ProcessState.State(thread1.getState()));
        }

        Thread.sleep(200);

        System.out.println(thread1.getName() + " " + ProcessState.State(thread1.getState()));

        System.out.println(thread.getName() + " Сейчас я " + ProcessState.State(thread.getState()));

        synchronized (monitor) {
            monitor.notify();
            thread1.interrupt();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.getName() + " " + ProcessState.State(thread.getState()));
    }
}

