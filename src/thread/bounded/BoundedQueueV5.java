package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;

public class BoundedQueueV5 implements BoundedQueue {

    private final Lock lock = new ReentrantLock();
    private final Condition producerCondition = lock.newCondition(); // ReentrantLock가 사용하는 thread 락 대기 공간
    private final Condition cunsumerCondition = lock.newCondition(); // ReentrantLock가 사용하는 thread 락 대기 공간
    private final Queue<String> queue = new ArrayDeque<String>();
    private final int maxSize;

    public BoundedQueueV5(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public void put(String data) throws InterruptedException {
        lock.lock();

        try {
            while (queue.size() == maxSize) {
                log("[put] 큐가 가득 참, 생상자 대기 :" + data);
                producerCondition.await(); // 지정한 condition에 스레드상태를 대기상태로 보관runnable -> waiting , 락을 반납
                log("[put] 생산자 깨어남");
            }
            queue.offer(data);
            log("[put] 생산자 데이터 저장, cunsumerCondition.signal() 호출");
            cunsumerCondition.signal(); // 대기하는 스레드 깨우기
        } finally {
            lock.unlock();
        }


    }

    @Override
    public String take() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                log("[take] 큐가 가득 참, 소비자 대기");
                cunsumerCondition.await();
            }

            String poll = queue.poll();
            log("[take] 소비사 데이터 획득 producerCondition.signal() 호출");
            producerCondition.signal(); // Reentrant Lock 을 가지고 있는 애가 호출 해야된다.
            return poll;
        } finally {
            lock.unlock();
        }
    }

    public String toString() {
        return queue.toString();
    }
}
