package thread.start;

public class HelloThreadMain {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " : main() start");
        System.out.println(Thread.currentThread().getName() + " : start() 호출 전");
        new HelloThread().start();
//        new HelloThread().run(); // start()를 호출해야 별도의 스레드 생성 후 호출 run() 은 현재 쓰레드에서 실행
        System.out.println(Thread.currentThread().getName() + " : start() 호출 후");
        System.out.println(Thread.currentThread().getName() + " : main() end");
    }
}
