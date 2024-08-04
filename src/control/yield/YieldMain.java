package control.yield;

import static util.ThreadUtils.sleep;

public class YieldMain {

    static final int THREAD_CNT = 1000;
    public static void main(String[] args) {
        for (int i = 0; i < THREAD_CNT; i++) {
            Thread thread = new Thread(new MyRunnable());
            thread.start();
        }
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i <10; i++) {
                System.out.println(Thread.currentThread().getName() + " - "  +  i);
                //1. empty

//                 sleep(1); // tiemd-waiting -> runnable 실행 스케줄러에서 제외되었다가 들어옴


                Thread.yield(); // running -> wait 양보할 thread가 없으면 본인이 계속 실행됨
            }
        }
    }
}
