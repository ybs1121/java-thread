package thread.sync.lock;

import java.util.concurrent.locks.LockSupport;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class LockSupportMainV1 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new ParkTest(), "t1");
        t1.start();

        // 잠시 대시하여 t1이 park 상태에 빠질 시간을 준다.
        sleep(100);
        log("t1 state : " + t1.getState());

        log("main -> unpark(thread)");
        LockSupport.unpark(t1); // WAITING -> RUNNABLE
//        t1.interrupt(); // interrupt() 로 상태 변경
    }



    static class ParkTest implements Runnable {

        @Override
        public void run() {
            log("park 시작");
            LockSupport.park(); // 쓰레드의 실행 상태를 WAITING으로 변경한다.
            log("park 상태 " + Thread.currentThread().getState());
            log("인터럽트 상태 " + Thread.currentThread().isInterrupted());
            log("park 종료");
        }
    }
}
