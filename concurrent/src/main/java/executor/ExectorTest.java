package executor;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by benwq on 2017/6/20.
 */
public class ExectorTest {
    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        Runnable r = () -> {
            try {
                for(int k=0;k<1000;k++)
                    Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        for(int i=0;i<20;i++){
            new Thread(r).start();
        }
        executor.shutdown();
        System.out.println(executor.isShutdown());
    }

}
