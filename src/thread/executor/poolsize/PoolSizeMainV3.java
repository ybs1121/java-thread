package thread.executor.poolsize;

import thread.executor.RunnableTask;

import java.util.concurrent.*;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class PoolSizeMainV3 {

    public static void main(String[] args) {
        int nThreads = 2;
//        ExecutorService es = Executors.newCachedThreadPool();

        ThreadPoolExecutor es = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                3L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());

        log("pool 생성");
        printState(es);

        for (int i = 1; i <= 100; i++) {
            String taskName = "task" + i;
            es.execute(new RunnableTask(taskName));
            printState(es,taskName);
        }

        sleep(3000);
        log("== 작업 수행 완료 ==");
        printState(es);

        sleep(3000);
        log("== 쓰레드 대기시간 초과 ==");
        printState(es);

        es.close();
        log("== shutdown 완료 ==");
        printState(es);
    }
}
