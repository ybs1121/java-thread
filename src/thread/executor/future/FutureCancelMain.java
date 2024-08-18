package thread.executor.future;

import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class FutureCancelMain {

//    private static boolean mayInterruptIfRunning = false;
    private static boolean mayInterruptIfRunning = true;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<String> future = es.submit(new MyTask());
        log("Future.state: " + future.state());

        // 일정 시간  후 취소 시도
        sleep(3000);

        //cancel() 호출
        log("future.cancel( " + mayInterruptIfRunning + " )  호출");
        boolean cancelResult = future.cancel(mayInterruptIfRunning);
        log("cancel( " + mayInterruptIfRunning + " ) result: " + cancelResult);

        //결과 확인
        try {
            log("Future.get(): " + future.get());
        } catch (CancellationException ce) {
            log("CancellationException Future은 이미 취소");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        es.close();

    }


    static class MyTask implements Callable<String> {

        @Override
        public String call() {
            try {
                for (int i = 0; i < 10; i++) {
                    log("작업중 : " + i);
                    Thread.sleep(1000); // 1초 동안 Sleep
                }
            } catch (InterruptedException e) {
                log("인터럽트 발생");
                return "INTERRUPTED";
            }


            return "COMPLETED";
        }
    }
}
