package com.wqb.mem;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author benwq
 * @Description:本地直接内存溢出案例
 * @Date: 10:18 2018/4/9
 */
public class DirectMemoryOOM {
    private static final int _1KB = 1024;
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
