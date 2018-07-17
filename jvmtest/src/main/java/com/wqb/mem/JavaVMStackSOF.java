package com.wqb.mem;

/**
 * @author benwq
 * @Description:通过单线程增加方法栈深度永远无法获取OOM异常，只能获取SOF异常
 * @Date: 14:00 2018/4/4
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }

    }
}
