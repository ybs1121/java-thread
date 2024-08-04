package thread.control.printer;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static util.MyLogger.log;

public class MyPrinterV3 {

    public static void main(String[] args) {

        Printer printer = new Printer();
        Thread printerThread = new Thread(printer, "Printer Thread");

        printerThread.start();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            log("프린트 할 문서 입력 (q는 종료 ): ");
            String input = scanner.nextLine();
            if (input.equals("q")) {
                printerThread.interrupt();
                break;
            }

            printer.addJob(input);
        }
    }

    static class Printer implements Runnable {

        Queue<String> jobQueue = new ConcurrentLinkedQueue<>();

        @Override
        public void run() {

            while (!Thread.interrupted()) {
                try {
                    if (jobQueue.isEmpty()) {
                        continue;
                    }

                    String job = jobQueue.poll();
                    log("출력 시작 : " + job + ", 대기 문서 : " + jobQueue);
                    Thread.sleep(3000);
                    log("출력 완료");
                } catch (InterruptedException e) {
                    log("인터럽트 발생");
                    break;
                }

            }
        }

        public void addJob(String job) {
            jobQueue.add(job);
        }
    }

}
