package thread.cas.increment;

import java.util.ArrayList;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class IncrementThreadMain {

    public static final int THREAD_COUNT = 1000;

    public static void main(String[] args) throws InterruptedException {
        test(new BasicInteger());
        test(new VolatileInteger());
        test(new SyncInteger());
        test(new MyAtomicInteger());
    }

    private static void test(IncrementInteger incrementInteger) throws InterruptedException {
        Runnable runnable = new Runnable(){
            public void run() {
                sleep(10); // 너무 빠르니까 잠깐 쉬었다가 실행, 다른 스레드랑 같이 실행하기 위해
                incrementInteger.increment();
            }
        };

        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        int result = incrementInteger.get();
        log(incrementInteger.getClass().getSimpleName() + " result: " + result);
    }
}
