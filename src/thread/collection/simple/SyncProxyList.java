package thread.collection.simple;

import java.util.List;

public class SyncProxyList implements SimpleList {

    private SimpleList target;

    public SyncProxyList(SimpleList target) {
        this.target = target;
    }

    @Override
    public synchronized int size() {
        return target.size();
    }

    @Override
    // 1. 락 획득
    public synchronized void add(Object o) {
        // 2. 원본 메소드 호출
        target.add(o);
        // 3. 원본 메소드 반납
        // 4. 락 반납
    }

    @Override
    public synchronized Object get(int index) {
        return target.get(index);
    }

    @Override
    public String toString() {
        return target.toString() + " by " + this.getClass().getSimpleName();
    }
}
