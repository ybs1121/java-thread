package thread.sync;

public interface BackAccount {

    boolean withdraw(int amount) throws InterruptedException;

    int getBalance();
}
