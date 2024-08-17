package thread.collection.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedListMain {
    public static void main(String[] args) {
        List<String> list = Collections.synchronizedList(new ArrayList<>());

        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(list.getClass());
        System.out.println("list : " + list);

    }
}
