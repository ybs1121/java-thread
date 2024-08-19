package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import static util.MyLogger.log;

public abstract class ExecutorUtils {

    public static void printState(ExecutorService executorService) {
        if (executorService instanceof ThreadPoolExecutor poolExecutor) { // 바로 CASTING
            int poolSize = poolExecutor.getPoolSize();
            int activeCount = poolExecutor.getActiveCount();
            int queueSize = poolExecutor.getQueue().size();
            long completedTaskCount = poolExecutor.getCompletedTaskCount();
            log("[pool =  " + poolSize + ", active = " + activeCount
                    + ", queue = " + queueSize + ", completed = " + completedTaskCount + "]");
        } else {
            log(executorService);
        }

    }

    public static void printState(ExecutorService executorService,String taskName) {
        if (executorService instanceof ThreadPoolExecutor poolExecutor) { // 바로 CASTING
            int poolSize = poolExecutor.getPoolSize();
            int activeCount = poolExecutor.getActiveCount();
            int queueSize = poolExecutor.getQueue().size();
            long completedTaskCount = poolExecutor.getCompletedTaskCount();
            log(taskName + " -> [pool =  " + poolSize + ", active = " + activeCount
                    + ", queue = " + queueSize + ", completed = " + completedTaskCount + "]");
        } else {
            log(executorService);
        }

    }
}
