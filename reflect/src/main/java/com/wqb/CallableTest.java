package callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by benwq on 2017/6/23.
 */
//public class CallableTest {
//    public static void main(String[] args) {
//        MyCallable myCallable = new MyCallable();
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        try {
//            Future<Integer> future = executorService.submit(myCallable);
//            SECONDS.sleep(1);
//            System.out.println(future.isDone());
//            future.get();
//            future.cancel(true);
//        }catch (InterruptedException e){
//
//        }catch (ExecutionException e) {
////            throw e.getCause();
//        }
//    }
//}
