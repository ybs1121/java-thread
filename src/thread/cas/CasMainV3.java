package thread.cas;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class CasMainV3 {

    private static final int THREAD_CNT = 100;

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);

        System.out.println("start value = " + atomicInteger.get());


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                incrementAndGet(atomicInteger);
            }
        };

        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREAD_CNT; i++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int result = atomicInteger.get();
        System.out.println(atomicInteger.getClass().getSimpleName() +  " result = " + result);
    }

    private static int incrementAndGet(AtomicInteger atomicInteger) {
        int getValue;
        boolean result;
        do {
            getValue = atomicInteger.get();
            sleep(10); // Thread 동시 실행을 위한 대기
            log("getValue = " + getValue);
            result = atomicInteger.compareAndSet(getValue, getValue + 1);
            log("result : " + result);
        } while (!result);

        return getValue + 1;
    }
}
