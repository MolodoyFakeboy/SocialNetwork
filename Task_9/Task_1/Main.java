package Task_1;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        final Object monitor = new Object();

        Thread thread = new Thread(() -> {
            System.out.println(" Поток ожидатель начинает работу его состояние " + ProcessState.State(Thread.currentThread().getState()));
            try {
                synchronized (monitor) {
                    monitor.wait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Поток ожидатель");

        System.out.println("Состояние потока Ожидателя до начала работы : " + ProcessState.State(thread.getState()));

        thread.start();

        Thread.sleep(200);

        System.out.println(thread.getName() + " " + ProcessState.State(thread.getState()));

        synchronized (monitor) {
            monitor.notify();
            System.out.println(thread.getName() + " " + ProcessState.State(thread.getState()));
        }

        Thread.sleep(200);
        System.out.println(thread.getName() + " " + ProcessState.State(thread.getState()));


        synchronized (monitor) {
            monitor.notify();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.getName() + " " + ProcessState.State(thread.getState()));
    }

}

