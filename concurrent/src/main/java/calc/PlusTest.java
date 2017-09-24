package calc;

import java.util.regex.Pattern;

/**
 * Created by benwq on 2017/7/7.
 */
public class PlusTest {
    public static void main(String[] args) {
        int a = 5,i;
        i = ++a + ++a + a++;
        System.out.println("a:"+a);
        System.out.println("i:"+i);
        i = a++ + ++a + ++a;
        System.out.println("a:"+a);
        System.out.println("i:"+i);
        a = ++a + ++a + a++;
        System.out.println("a:"+a);
        System.out.println("i:"+i);
        Pattern.compile("");
    }
}
