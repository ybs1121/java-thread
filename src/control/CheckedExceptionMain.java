package control;

import util.ThreadUtils;

public class CheckedExceptionMain {

    public static void main(String[] args) {

    }

    static class CheckedRunnable implements Runnable {

        @Override
        public void run() {
            ThreadUtils.sleep(1000);
        }
    }
}
