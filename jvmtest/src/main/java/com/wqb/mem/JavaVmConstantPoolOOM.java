package com.wqb.mem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author benwq
 * @Description:Java8中将常量池从永久带中转移到堆中，因此Java8中PermSize和MaxPermSize两个参数无效
 * -Xms20M -Xmx20M -XX:PermSize=8m -XX:MaxPermSize=8m
 * @Date: 15:37 2018/4/4
 */
public class JavaVmConstantPoolOOM {
    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
        List<String> list = new ArrayList<>();
        int i=0;
        while (true){
            list.add(String.valueOf(i).intern());
        }
    }
}
