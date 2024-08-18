package thread.executor.future;

import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class FutureExceptionMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(1);
        log("작업전달");
        Future<Integer> future = es.submit(new ExCallable());
        sleep(1000); // 잠시 대기

        try {
            log("future.get() 호출 시도, future.state() :  " + future.state());
            Integer i = future.get();
            log("result value : " + i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log("e = " + e);
            Throwable cause = e.getCause(); // 원본 예외
            log("cause = " + cause);
        }

    }

    static class ExCallable implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            log("Callable 에외 발생");
            throw new IllegalStateException("ex!");
        }
    }
}
