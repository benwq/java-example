package iterruptedexception;

import org.junit.Test;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 测试说明：本例子用于测试线程A、B阻塞与C线程object对象后，C线程被中断
 * 测试结果：C被中断后，A、B线程的状态为阻塞，无法解除阻塞
 */
public class IterruptTest2 {

    class ThreadC implements Runnable{
        BlockingDeque<Integer> blockingDeque = new LinkedBlockingDeque<>(100);

        public void push(Integer i){
            blockingDeque.push(i);
        }

        @Override
        public void run() {
            while (true){
                try {
                    System.out.println("线程C运行："+blockingDeque.take());
                } catch (InterruptedException e) {

                }finally{

                }
            }
        }
    }

    @Test
    public void test(){
        ThreadC threadC = new ThreadC();
        new Thread(threadC).start();
        new Thread(()->{
            for(int i=0;i<100;i++) {
                threadC.push(i);
                System.out.println("线程A运行："+i);
            }
        }).start();
        new Thread(()->{
            for(int i=100;i<200;i++){
                threadC.push(i);
                System.out.println("线程B运行："+i);
            }
        }).start();
    }
}
