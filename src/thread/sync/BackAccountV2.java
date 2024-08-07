package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BackAccountV2 implements BackAccount {

    private int balance = 0;

    public BackAccountV2(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public synchronized boolean withdraw(int amount) { // 모니터 락 lock 획득 해서 로직 수행
        log("거래 시작 : " + getClass().getSimpleName());
        // 잔고가 출금액 보다 적으면, 진행 X

        // ==임계 영역 시작 ==/
        log("[검증시작] 출금액 : " + amount + " , " + "잔액 : " + balance);
        if (balance < amount) {
            log("[검증 실패] 출금액 : " + amount + " , " + "잔액 : " + balance);
            return false;
        }
        log("[검증 완료] 출금액 : " + amount + " , " + "잔액 : " + balance);
        sleep(1000); // 출금에 걸리는 시간으로 가정

        balance -= amount;
        log("[출금 완료] 출금액 : " + amount + " , " + "잔액 : " + balance);
        // ==임계 영역 종료 ==/

        log("거래 종료");


        return true;
    }

    @Override
    public synchronized int getBalance() {
        return this.balance;
    }
}
