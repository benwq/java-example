package com.wqb.mem;

/**
 * @author benwq
 * @Description:栈帧Slot Gc测试
 * -verbose:gc
 * @Date: 13:38 2018/7/13
 */
public class SlotMemoryTest {
    public static void main(String[] args) {
        for(int i=0;i<100;i++) {
            {
                byte[] my_byte = new byte[64 * 1024 * 1024];
            }
//            int a = 0;
            System.gc();
        }
    }
}
