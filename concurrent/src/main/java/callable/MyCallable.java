package callable;

import java.util.concurrent.Callable;

/**
 * Created by benwq on 2017/6/23.
 */
public class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println(2);
        return 1;
    }
}
