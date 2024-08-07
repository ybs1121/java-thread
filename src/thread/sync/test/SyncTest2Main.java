package thread.sync.test;

import static util.MyLogger.log;

public class SyncTest2Main {

    public static void main(String[] args) {

        MyCounter myCounter = new MyCounter();

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                myCounter.count();
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();


    }

    static class MyCounter {

        public void count(){
            int localValue = 0; // 지역변수는 다른 쓰레드과 공유하지 않는다.
            for (int i = 0; i < 1000; i++) {
                localValue += 1;

            }

            log("결과:  "  + localValue);
        }




    }
}
