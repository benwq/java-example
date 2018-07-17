package com.wqb.mem;

/**
 * @author benwq
 * @Description:老年代担保机制测试,此参数只在 jdk6 Update24之前有效
 * @Date: 19:16 2018/4/19
 * -XX:+PrintGCDetails -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:-HandlePromotionFailure
 */
public class HandlePromotionTest {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6, allocation7;
        allocation1 = new byte[_1MB * 2];
        allocation2 = new byte[_1MB * 2];
        allocation3 = new byte[_1MB * 2];
        allocation3 = null;
        allocation4 = new byte[_1MB * 2];
        allocation5 = new byte[_1MB * 2];
        allocation6 = new byte[_1MB * 2];
        allocation4 = null;
        allocation5 = null;
        allocation6 = null;
        allocation7 = new byte[_1MB * 2];
    }
}
