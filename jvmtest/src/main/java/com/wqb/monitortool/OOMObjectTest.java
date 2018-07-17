package com.wqb.monitortool;

import java.util.ArrayList;
import java.util.List;

/**
 * @author benwq
 * @Description:以64kb/s的速度往堆中添加数据
 * @Date: 14:51 2018/4/20
 * -Xms100M -Xmx100M -XX:+UserSerialGC
 */
public class OOMObjectTest {
    public static void fillHeap(int count) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for(int i=0;i<count;i++){
            list.add(new OOMObject());
            Thread.sleep(50);
        }
//        System.gc();
    }

    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
        System.gc();
    }
}
