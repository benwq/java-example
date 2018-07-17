package com.wqb.mem;

/**
 * @author benwq
 * @Description:此处添加三个2MB的对象，1个4MB的对象,测试新生代区的GC
 * @Date: 15:09 2018/4/19
 */
public class EdenGc {
    private final static int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];
    }
}
