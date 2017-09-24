package runnable;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by benwq on 2017/7/7.
 */
public class ReentrantLockTest {
    Lock lock = new ReentrantLock();
    Condition notEmpty = lock.newCondition();
    Condition notFull = lock.newCondition();
    int count =1;
    Integer waitObj = 1;

    public void loopLock(){
        while(true&&count<=2) {
            try {
                lock.lock();
                Thread.sleep(2000);
                count++;
            } catch (InterruptedException e) {

            } finally {
                lock.unlock();
            }
        }
    }

    public void print(){
        while(true) {
            lock.lock();
            System.out.println(count++);
            lock.unlock();
        }
    }

    public void tryLock(){
        while (true) {
            if (lock.tryLock()) {
                System.out.println("tryLock() sucess!");
            } else {
                System.out.println("tryLock() failed!");
            }
        }
    }

    public void tryLock(Long time,TimeUnit timeUnit){
        try {
            if(lock.tryLock(time,timeUnit)) {
                try {
                    System.out.println("tryLock(time,timeUnit) sucess!");
                } finally {
                    lock.unlock();
                }
            }else{
                System.out.println("tryLock(time,timeUnit) failed!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void await(){
        lock.lock();
        try {
            notEmpty.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void lock(){
        lock.lock();
    }

    public void unlock(){
        lock.unlock();
    }

    public static void main(String[] args) {
        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
//        new Thread(()->reentrantLockTest.await()).start();
        reentrantLockTest.await();
        new Thread(()->reentrantLockTest.print()).start();
//        new Thread(()->reentrantLockTest.tryLock(5L,TimeUnit.SECONDS)).start();
    }
}
