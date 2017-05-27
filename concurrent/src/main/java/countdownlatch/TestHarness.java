package countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by benwq on 2017/5/26.
 */
public class TestHarness {
    public long timeTasks(int nThreads,final Runnable task) throws InterruptedException{
        final CountDownLatch startGrade = new CountDownLatch(1);
        final CountDownLatch endGrade = new CountDownLatch(nThreads);
        long start = System.nanoTime();
        endGrade.await();
        long end = System.nanoTime();
        return end-start;
    }
}
