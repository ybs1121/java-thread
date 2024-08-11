package thread.bounded;

import static util.MyLogger.log;

public class ConsumerTask implements Runnable {

    private BoundedQueue queue;

    public ConsumerTask(BoundedQueue queue) {
        this.queue = queue;
    }

    public BoundedQueue getQueue() {
        return queue;
    }


    @Override
    public void run() {
        log("[소비 시도]    ?" + " <- " + queue);
        String data = null;
        try {
            data = queue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log("[소비 완료] " + data + " <- " + queue);
    }
}
