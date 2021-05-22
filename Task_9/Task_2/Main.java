package Task_2;

public class Main {
    public static void main(String[] args) {

        final Object monitor = new Object();

        System.out.println("Начать перекличку");

        System.out.println("........");

        MyThread thread1 = new MyThread("The Life Of Pablo", monitor);

        MyThread thread2 = new MyThread("JESUS IS KING", monitor);

        thread1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();

    }
}
