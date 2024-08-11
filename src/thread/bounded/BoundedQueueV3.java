package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BoundedQueueV3 implements BoundedQueue {

    private final Queue<String> queue = new ArrayDeque<String>();
    private final int maxSize;

    public BoundedQueueV3(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public synchronized void put(String data) throws InterruptedException {
        while (queue.size() == maxSize) {
            log("[put] 큐가 가득 참, 생상자 대기 :" + data);
            wait(); // runnable -> waiting , 락을 반납
            log("[put] 생산자 깨어남");
        }
        queue.offer(data);
        log("[put] 생산자 데이터 저장, notify() 호출");
        notify(); // 대기 쓰레드 -> wait -> blocked
    }

    @Override
    public synchronized String take() throws InterruptedException {
        while (queue.isEmpty()) {
            log("[take] 큐가 가득 참, 소비자 대기");
            wait();
        }

        String poll = queue.poll();
        log("[take] 소비사 데이터 획득 notify() 호출");
        notify(); // 대기 스래드 , wait -> blocked

        return poll;
    }

    public String toString() {
        return queue.toString();
    }
}
