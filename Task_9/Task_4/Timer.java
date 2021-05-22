package Task_4;

import java.util.Date;

public class Timer extends Thread {
     private long time;

    public Timer(long time) {
        this.time = time;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Date date = new Date();
                System.out.println(date);
                Timer.sleep(time*1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}



