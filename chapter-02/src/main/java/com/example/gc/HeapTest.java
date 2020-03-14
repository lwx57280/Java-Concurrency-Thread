package com.example.gc;

import java.util.ArrayList;

/**
 * 内存泄漏
 */
public class HeapTest {

    // 100KB
    byte[] a = new byte[1024 * 100];

    public static void main(String[] args) throws InterruptedException {
        ArrayList<HeapTest> heapTests = new ArrayList<>();
        while (true) {
            heapTests.add(new HeapTest());
            Thread.sleep(10);
        }
    }
}
