package thread.sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BackAccountV6 implements BackAccount {

    private int balance = 0;

    private final Lock lock = new ReentrantLock();

    public BackAccountV6(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public boolean withdraw(int amount) throws InterruptedException {
        log("거래 시작 : " + getClass().getSimpleName());
        // 잔고가 출금액 보다 적으면, 진행 X
        if(!lock.tryLock(500, TimeUnit.MILLISECONDS)) { // InterruptedException 을 던진다
            log("[진입 실패] 이미 처리 중인 작업이 있습니다");
            return false;
        }
        // ==임계 영역 시작 ==/

        try {
            log("[검증시작] 출금액 : " + amount + " , " + "잔액 : " + balance);
            if (balance < amount) {
                log("[검증 실패] 출금액 : " + amount + " , " + "잔액 : " + balance);
                return false;
            }
            log("[검증 완료] 출금액 : " + amount + " , " + "잔액 : " + balance);
            sleep(1000); // 출금에 걸리는 시간으로 가정

            balance -= amount;
            log("[출금 완료] 출금액 : " + amount + " , " + "잔액 : " + balance);
        } finally {
            lock.unlock();
        }

        // ==임계 영역 종료 ==/

        log("거래 종료");


        return true;
    }

    @Override
    public int getBalance() {
        lock.lock(); // ReentrantLock 을 이용해서 락 걸기
        try {
            return this.balance;
        } finally {
            lock.unlock();
        }

    }
}
