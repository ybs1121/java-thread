package thread.java;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class MapMain {

    public static void main(String[] args) {

        Map<Integer, String> map = new ConcurrentHashMap<>();
        map.put(3, "d3");
        map.put(1, "d1");
        map.put(2, "d2");

        System.out.println("map = " + map);

        Map<Integer, String> map2 = new ConcurrentSkipListMap<>();
        map2.put(3, "d3");
        map2.put(1, "d1");
        map2.put(2, "d2");

        System.out.println("map2 = " + map2);
    }
}
