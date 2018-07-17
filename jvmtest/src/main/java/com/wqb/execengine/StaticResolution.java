package com.wqb.execengine;

/**
 * @author benwq
 * @Description:方法静态解析演示
 * @Date: 18:28 2018/7/17
 */
public class StaticResolution {

    public static void sayHello() {
        System.out.println("hello World");
    }

    public static void main(String[] args) {
        StaticResolution.sayHello();
    }
}
