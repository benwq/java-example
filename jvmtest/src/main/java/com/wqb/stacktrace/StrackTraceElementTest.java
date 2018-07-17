package com.wqb.stacktrace;

import java.util.Map;

public class StrackTraceElementTest {
    public static void main(String[] args) {
        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
        System.out.println(1);
    }
}
