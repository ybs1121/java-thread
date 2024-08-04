package thread.sync;

public class WithdrawTask implements Runnable {

    private BackAccount backAccount;
    private int amount;

    public WithdrawTask(BackAccount backAccount, int amount) {
        this.backAccount = backAccount;
        this.amount = amount;
    }

    @Override
    public void run() {
        backAccount.withdraw(amount);
    }
}
