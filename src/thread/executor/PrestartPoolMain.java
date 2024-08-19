package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static thread.executor.ExecutorUtils.printState;
import static util.ThreadUtils.sleep;

public class PrestartPoolMain {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(1000);
        printState(es);

        ThreadPoolExecutor tpe = (ThreadPoolExecutor) es;
        tpe.prestartAllCoreThreads(); // 미리 스레드를 생성해놔 시간을 조금이나마 줄인다.
        sleep(100);
        printState(es);
        tpe.shutdown();


    }
}
