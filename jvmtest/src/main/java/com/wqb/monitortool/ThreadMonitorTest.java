package com.wqb.monitortool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author benwq
 * @Description:用于示范jconsole中线程监视的几种异常情况、
 * 造成线程长时间停顿的原因有：等待外部资源、死循环、锁等待
 * @Date: 15:26 2018/4/20
 */
public class ThreadMonitorTest {
    public static void createBusyThread(){
        new Thread(()->{
            while (true){
                ;
            }
        },"testBusyThread").start();
    }

    public static void createLockThread(final Object lock){
        new Thread(()->{
            synchronized (lock){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"testLockThread").start();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        createBusyThread();
        br.readLine();
        createLockThread(new Object());
    }
}
