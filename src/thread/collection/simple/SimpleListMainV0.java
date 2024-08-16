package thread.collection.simple;

import java.util.ArrayList;
import java.util.List;

public class SimpleListMainV0 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        // 스레드 1 , 스레드 2가 동시에 실행한다고 가정
        list.add("a"); // 스레드 1가 실행
        list.add("b"); // 스레드 2가 실행

        System.out.println(list);
    }
}
