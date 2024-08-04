package thread;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class volatileFlagMain {

    public static void main(String[] args) {
            MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        log("runFlag = "  + task.runFlag);
        thread.start();

        sleep(1000);
        log("runFlag를 false로 변경 시도");
        task.runFlag = false;
        log("runFlag = "  + task.runFlag);
        log("main 종료");
    }

    static class MyTask implements Runnable {
//        boolean runFlag = true; // 기대는 종료지만 task가 종료가 안된다. -> 코어의 캐쉬 메모리의 runFlag를 바라보고 있기때문
        //컨텍스트 스위칭이 발생할 때 캐쉬에 있는게 메인메모리로 주로 갱신된다.
        volatile boolean runFlag = true; // volatile 키워드를 사용하면 캐쉬메모리를 바라보지 않고 메인메모리를 바라본다.

        @Override
        public void run() {
            log("task 시작");
            while (runFlag) {
                //runFlag 가 false로 변하면 탈출
            }
            log("task 종료");
        }
    }
}
