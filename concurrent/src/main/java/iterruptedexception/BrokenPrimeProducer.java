package iterruptedexception;

import java.math.BigInteger;
import java.util.BitSet;
import java.util.concurrent.BlockingDeque;

/**
 * Created by benwq on 2017/7/6.
 */
public class BrokenPrimeProducer extends Thread{
    private final BlockingDeque<BigInteger> queue;
    private volatile boolean cancelled = false;

    public BrokenPrimeProducer(BlockingDeque<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancelled){
            try {
                queue.put(p.nextProbablePrime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void cancel(){
        cancelled = true;
    }
}
