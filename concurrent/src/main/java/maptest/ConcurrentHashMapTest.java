package maptest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by benwq on 2017/5/8.
 */
public class ConcurrentHashMapTest {
    static ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<String,Object>();

    public static void main(String[]args){
        map.putIfAbsent("a",true);
        map.put("a","a");
        map.get("a");
    }
}
