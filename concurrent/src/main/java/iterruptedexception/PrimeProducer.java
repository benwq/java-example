package iterruptedexception;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by benwq on 2017/7/6.
 * 尝试中断机制
 */
public class PrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> queue;

    public PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!Thread.currentThread().isInterrupted())
                    queue.put(p = p.nextProbablePrime());
        } catch (InterruptedException e) {

        }
    }

    public void cancel(){
        interrupt();
    }

    public BlockingQueue<BigInteger> getQueue() {
        return queue;
    }

    public static void main(String[] args) {
        PrimeProducer primeProducer = new PrimeProducer(new ArrayBlockingQueue<BigInteger>(20));
        primeProducer.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        primeProducer.cancel();
        BlockingQueue<BigInteger> queue = primeProducer.getQueue();
        System.out.println(Arrays.toString(queue.toArray()));
    }
}
