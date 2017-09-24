import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by benwq on 2017/8/20.
 */
public class ReflectionTest {
    private static Map<String,Object> sessionMap = new HashMap<>();

    static {
        sessionMap.put("benwq",1);
    }

    public static void main(String[] args) {
        try {
            Field field = ReflectionTest.class.getDeclaredField("sessionMap");
            field.setAccessible(true);
            HashMap<String,Object> tempMap  = (HashMap<String, Object>) field.get(null);
            System.out.println(tempMap.get("benwq"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
