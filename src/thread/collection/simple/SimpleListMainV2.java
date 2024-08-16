package thread.collection.simple;

import static util.MyLogger.log;

public class SimpleListMainV2 {

    public static void main(String[] args) throws InterruptedException {
//        test(new BasicList());
//        test(new SyncList());

        test(new SyncProxyList(new BasicList()));
    }

    public static void test(SimpleList list) throws InterruptedException {
        log(list.getClass().getSimpleName());
        Runnable runnable = () -> {
            list.add("A");
            log("Thread 1 : list.add(A)");
        };

        Runnable runnable2 = () -> {
            list.add("B");
            log("Thread 2 : list.add(B)");
        };

        Thread thread = new Thread(runnable, "t1");
        Thread thread1 = new Thread(runnable2, "t2");

        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        log(list);

    }
}
