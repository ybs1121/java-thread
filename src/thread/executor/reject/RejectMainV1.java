package thread.executor.reject;

import thread.executor.RunnableTask;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static util.MyLogger.log;

public class RejectMainV1 {

    public static void main(String[] args) {
        ThreadPoolExecutor es = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS
                , new SynchronousQueue<>(), new ThreadPoolExecutor.AbortPolicy());
        // 예외 발생 거절
        es.submit(new RunnableTask("task1"));
        try {
            es.submit(new RunnableTask("task1"));
        } catch (RejectedExecutionException e) {
            log("요청 초과");
            log(e);
        }


        es.close();
    }
}
