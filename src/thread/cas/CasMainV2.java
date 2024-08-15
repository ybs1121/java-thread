package thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

import static util.MyLogger.log;

public class CasMainV2 {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);

        System.out.println("start value = " + atomicInteger.get());


        // incrementAndGet 구현
        int result1 = incrementAndGet(atomicInteger);
        System.out.println("result1 = " + result1 + ", value = " + atomicInteger.get());

        int result2 = incrementAndGet(atomicInteger);
        System.out.println("result2 = " + result2 + ", value = " + atomicInteger.get());

    }

    private static int incrementAndGet(AtomicInteger atomicInteger) {
        int getValue;
        boolean result;
        do {
            getValue = atomicInteger.get();
            log("getValue = " + getValue);
            result = atomicInteger.compareAndSet(getValue, getValue + 1);
            log("result : " + result);
        }while (!result);

        return getValue + 1;
    }
}
