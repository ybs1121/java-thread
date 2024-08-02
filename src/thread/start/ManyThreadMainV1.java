package thread.start;

import static util.MyLogger.log;

public class ManyThreadMainV1 {
    public static void main(String[] args) {
        log("main() start");
        HelloRunnable helloRunnable = new HelloRunnable();
        Thread t1 = new Thread(helloRunnable);
        t1.start();
        Thread t2 = new Thread(helloRunnable);
        t2.start();
        Thread t3 = new Thread(helloRunnable);
        t3.start();
        log("main() end");
    }
}
