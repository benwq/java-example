package callable;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

/**
 * Created by benwq on 2017/7/4.
 * ExecutorCompletionService.class test
 */
public class ExComSerTest {
    public static void main(String[] args) {
        Executor executor = Executors.newFixedThreadPool(8);
        ExecutorCompletionService<Object> executorCompletionService = new ExecutorCompletionService<Object>(executor);
//        executorCompletionService

    }
}
