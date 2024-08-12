package thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static util.MyLogger.log;

public class BoundedQueueV6_3 implements BoundedQueue {

    private BlockingQueue<String> queue;

    public BoundedQueueV6_3(int max) {
        this.queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) throws InterruptedException {
        boolean offer = queue.offer(data, 1, TimeUnit.NANOSECONDS); // 성공하면 true , 아님 false
        log("저장 시도 결과 " + offer);
    }

    @Override
    public String take() throws InterruptedException {
        return queue.poll(2, TimeUnit.SECONDS); // 데이터가 없으면 null을 리턴한다.
    }


    @Override
    public String toString() {
        return "BoundedQueueV6_1{" +
                "queue=" + queue +
                '}';
    }
}
