package com.wqb.monitortool;

/**
 * @author benwq
 * @Description:该测试用例演示了一个死锁代码样例
 * @Date: 10:29 2018/4/23
 */
public class DeadLockTest {

    static class SynAddRunnable implements Runnable{
        int a,b;

        public SynAddRunnable(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            synchronized (Integer.valueOf(a)){
                synchronized (Integer.valueOf(b)){
                    System.out.println(a+b);
                }
            }
        }
    }

    public static void main(String[] args) {
        for(int i=0;i<100;i++) {
            new Thread(new SynAddRunnable(1, 2)).start();
            new Thread(new SynAddRunnable(2, 1)).start();
        }
    }
}
