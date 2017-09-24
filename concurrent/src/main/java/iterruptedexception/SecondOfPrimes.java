package iterruptedexception;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by benwq on 2017/7/21.
 */
public class SecondOfPrimes {
    private static final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(4);

    public static void timeRun(Runnable r, long timeOut, TimeUnit timeUnit){
        Thread thread = Thread.currentThread();
        cancelExec.schedule(()-> thread.interrupt(),timeOut,timeUnit);
        r.run();
    }
}
