package thread.start;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileCountMain {

    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        sleep(1000);
        task.flag = false;
        log("flage = " + task.flag + ", count = " + task.cnt + " in main");
    }

    static class MyTask implements Runnable {

//        boolean flag = true;
//        long cnt;

        volatile boolean flag = true;  // 메모리에 직접 접근하기 때문에 성능은 조금 떨어진다
        volatile long cnt;

        @Override
        public void run() {
            while (flag) {
                //1억번에 한번씩 출력
                cnt++;
                if (cnt % 100_000_000 == 0) {

                    log("flage = " + flag + ", count = " + cnt + " in while");
                }
            }
            log("flage = " + flag + ", count = " + cnt + " 종료");

        }
    }
}
