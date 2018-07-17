package com.wqb.mem;

public class ReferenceCountingGc {
    public Object instance = null;

    private static final int _1MB = 1024 * 1024;
    private byte[] bigSize = new byte[2 * _1MB];

    public static void testGc(){
        ReferenceCountingGc referenceCountingGcA = new ReferenceCountingGc();
        ReferenceCountingGc referenceCountingGcB = new ReferenceCountingGc();
        referenceCountingGcA.instance = referenceCountingGcB;
        referenceCountingGcB.instance = referenceCountingGcA;

        referenceCountingGcA = null;
        referenceCountingGcB = null;
        System.gc();

        System.out.println(1);
    }

    public static void main(String[] args) {
        testGc();
    }
}
