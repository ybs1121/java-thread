package thread.start;

import static util.MyLogger.log;

public class ManyThreadMainV2 {
    public static void main(String[] args) {
        log("main() start");

        for (int i = 0; i < 100; i++) {
            HelloRunnable helloRunnable = new HelloRunnable();
            Thread t1 = new Thread(helloRunnable);
            t1.start();
        }
        log("main() end");
    }
}
