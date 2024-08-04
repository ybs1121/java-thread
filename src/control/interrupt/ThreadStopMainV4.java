package control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV4 {
    public static void main(String[] args) {

        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask, "work");
        thread.start();

        sleep(100);
        log("작업 중단 지시 thread.interrupt()");
        thread.interrupt(); // 호출 해도 InterruptedException 발생하는 거는 아니다.
        // sleep처럼 InterruptedException을 던지는 메소드를 호출하거나 호출 중일때 예외 발생
        log("work 스레드 인터럽트 상태 1 = " + thread.isInterrupted());


    }

    static class MyTask implements Runnable {

        @Override
        public void run() {

            while (!Thread.interrupted()) { // 상태확인 + 상태 변경
                log("작업 중");
            }

            log("work 스레드 인터럽트 상태 2 = " + Thread.currentThread().isInterrupted());

            try {
                log("자원정리");
                Thread.sleep(1000);
                log("자원정료");
            } catch (InterruptedException e) {
                log("자원 정리 실패 - 자원정리 중 인터럽트 발생");
                log("work 스레드 인터럽트 상태 3=" + Thread.currentThread().isInterrupted());
            }
            log("작업 종료");

        }
    }
}
