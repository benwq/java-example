package com.wqb.mem;

/**
 * @author benwq
 * @Description:为让栈发生内存溢出抛出oom，非sof
 * @Date: 14:48 2018/4/4
 */
public class JavaVMStackOOM {

    private int count = 1;
    private void dontStop() {
        while (true) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void stackLeakByThread(){
        while (true){
            new Thread(()-> dontStop()).start();
            System.out.println(count);
            count++;
        }
    }

    public static void main(String[] args) throws Throwable{
        JavaVMStackOOM javaVMStackOOM = new JavaVMStackOOM();
        javaVMStackOOM.stackLeakByThread();
    }
}
