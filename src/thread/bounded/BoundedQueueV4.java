package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;

public class BoundedQueueV4 implements BoundedQueue {

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition(); // ReentrantLock가 사용하는 thread 락  대기 공간
    private final Queue<String> queue = new ArrayDeque<String>();
    private final int maxSize;

    public BoundedQueueV4(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public void put(String data) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == maxSize) {
                log("[put] 큐가 가득 참, 생상자 대기 :" + data);
                condition.await(); // 지정한 condition에 스레드상태를 대기상태로 보관runnable -> waiting , 락을 반납
                log("[put] 생산자 깨어남");
            }
            queue.offer(data);
            log("[put] 생산자 데이터 저장, signal() 호출");
            condition.signal(); // 대기하는 스레드 깨우기
        } finally {
            lock.unlock();
        }


    }

    @Override
    public String take() throws InterruptedException {
        try {
            lock.lock();
            while (queue.isEmpty()) {
                log("[take] 큐가 가득 참, 소비자 대기");
                condition.await();
            }

            String poll = queue.poll();
            log("[take] 소비사 데이터 획득 signal() 호출");
            condition.signal();
            return poll;
        } finally {
            lock.unlock();
        }
    }

    public String toString() {
        return queue.toString();
    }
}
