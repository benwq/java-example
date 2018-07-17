package com.wqb.monitortool;

import java.util.HashMap;
import java.util.Map;

/**
 * @author benwq
 * @Description:测试一系列gc参数的作用
 * -XX:+PrintGCApplicationStoppedTime -XX:+PrintGCDateStamps -Xloggc:gclog.log -XX:+PrintReferenceGC
 * @Date: 16:29 2018/4/24
 */
public class GcParamTest {
    public static void main(String[] args) {
        Map<Long,Long> map = new HashMap<>();
        for(int i=0;i<8000000;i++){
            map.put(new Long(i),new Long(i));
        }
    }
}
