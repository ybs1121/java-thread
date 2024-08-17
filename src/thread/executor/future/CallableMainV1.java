package thread.executor.future;

import java.util.Random;
import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class CallableMainV1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<Integer> future = es.submit(new MyCallable()); // ThreadPool에 작업 전달
        Integer result = future.get();
        log("result: " + result);
        es.close();

    }

    static class MyCallable implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            log("MyCallable 시작");
            sleep(2000);
            int value = new Random().nextInt(10);
            log("creat value : " + value);
            log("MyCallable 완료");
            return value;
        }
    }
}
