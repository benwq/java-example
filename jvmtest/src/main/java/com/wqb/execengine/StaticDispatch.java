package com.wqb.execengine;

/**
 * @author benwq
 * @Description:方法静态分派演示
 * @Date: 18:17 2018/7/17
 */
public class StaticDispatch {

    static abstract class Human {

    }

    static class Woman extends Human {

    }

    static class Man extends Human {

    }

    public void sayHello(Human guy) {
        System.out.println("hello,guy!");
    }

    public void sayHello(Man man) {
        System.out.println("hello,gentleman");
    }

    public void sayHello(Woman woman) {
        System.out.println("hello,lady!");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch sd = new StaticDispatch();
        sd.sayHello(man);
        sd.sayHello(woman);
    }
}
