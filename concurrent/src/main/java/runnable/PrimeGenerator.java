package runnable;

import anno.ThreadSafe;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by benwq on 2017/7/6.
 * 素数创建器
 */
@ThreadSafe
public class PrimeGenerator implements Runnable {
    private final List<BigInteger> primes = new ArrayList<>();
    private volatile boolean calcelled;

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!calcelled){
            p = p.nextProbablePrime();
            synchronized (this) {
                primes.add(p);
            }
        }
    }

    public void cancel(){
        calcelled = true;
    }

    public synchronized List<BigInteger> get(){
        return new ArrayList<>(primes);
    }

    public static void main(String[] args) {
        PrimeGenerator primeGenerator = new PrimeGenerator();
        new Thread(primeGenerator).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            primeGenerator.cancel();
        }
        List<BigInteger> result = primeGenerator.get();
        for(BigInteger prime:result){
            System.out.println(prime);
        }
    }
}
