package thread.start.test;

import static util.MyLogger.log;

public class StartTest4Main {

    public static void main(String[] args) {

        TimeRunnable timeRunnable500 = new TimeRunnable(500,"B");
        TimeRunnable timeRunnable1000 = new TimeRunnable(1000,"A");

        new Thread(timeRunnable500).start();
        new Thread(timeRunnable1000).start();

    }


    static class TimeRunnable implements Runnable {

        int time;
        String name;

        TimeRunnable(int time, String name) {
            this.time = time;
            this.name = name;

        }

        @Override
        public void run() {
            while (true) {
                log("value : " + name);

                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
