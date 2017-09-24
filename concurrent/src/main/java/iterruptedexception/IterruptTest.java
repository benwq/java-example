package iterruptedexception;

import org.junit.Test;

/**
 * Created by benwq on 2017/7/6.
 */
public class IterruptTest {
    private Integer integer = 1;

    @Test
    public void test() {
        new Thread(new ThreadIterrupt()).start();
    }

    class ThreadSleep implements Runnable{
        @Override
        public void run() {
            while (true){
                try {
                    integer.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class ThreadIterrupt implements Runnable{
        @Override
        public void run() {
            System.out.println("before interrupt");
            Thread.currentThread().interrupt();
            System.out.println(Thread.currentThread().isInterrupted());
            for(int i=0;i<100000000;i++){
                System.out.println(i);
            }
            System.out.println("after interrupt");
        }
    }
}
