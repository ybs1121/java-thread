package thread.executor.future;

import java.util.Random;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class RunnableMain {

    public static void main(String[] args) throws InterruptedException {

        MyRunnable task = new MyRunnable();
        Thread thread = new Thread(task, "t1");
        thread.start();
        thread.join();
        int result = task.value;
        log("result: " + result);

    }

    static class MyRunnable implements Runnable {

        int value;

        @Override
        public void run() {
            log("runnable 시작");
            sleep(2000);
            value = new Random().nextInt(10);
            log("creat value : " + value);
            log("runnable 완료");
        }
    }
}
