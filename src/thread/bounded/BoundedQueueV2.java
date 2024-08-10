package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BoundedQueueV2 implements BoundedQueue {

    private final Queue<String> queue = new ArrayDeque<String>();
    private final int maxSize;

    public BoundedQueueV2(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public synchronized void put(String data) {
        while (queue.size() == maxSize) {
            log("[put] 큐가 가득 참, 생상자 대기 :" + data);
            sleep(1000);
        }
        queue.offer(data);
    }

    @Override
    public synchronized String take() {
        while (queue.isEmpty()) {
            log("[take] 큐가 가득 참, 소비자 대기");
            sleep(1000);
        }

        return queue.poll();
    }

    public String toString() {
        return queue.toString();
    }
}
