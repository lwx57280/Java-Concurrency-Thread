package com.concurrent.cacheline;

import sun.misc.Contended;
/**
 * @description:    缓存行
 * @author:         cong zhi
 * @createDate:     2022/5/30 14:25
 * @updateUser:     cong zhi
 * @updateDate:     2022/5/30 14:25
 * @updateRemark:   修改内容
 * @version:        v1.0
 */
public class CacheLine implements Runnable {

    public final static long ITERATIONS = 500L * 1000L * 100L;
    private int arrayIndex = 0;
    private static ValueNoPadding[] longs;

    public CacheLine(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void main(final String[] args) throws Exception {
        for (int i = 1; i < 10; i++) {
            System.gc();
            final long start = System.currentTimeMillis();
            /*内存屏障
            CPU在性能优化道路上导致的顺序一致性问题，在CPU层面无法被解决，原因是CPU只是一个运算工
            具，它只接收指令并且执行指令，并不清楚当前执行的整个逻辑中是否存在不能优化的问题，也就是说
            硬件层面也无法优化这种顺序一致性带来的可见性问题。*/
            runTest(i);
            System.out.println(i + " Threads, duration = " +
                    (System.currentTimeMillis() - start));
        }
    }

    private static void runTest(int NUM_THREADS) throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];
        longs = new ValueNoPadding[NUM_THREADS];
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new ValueNoPadding();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new CacheLine(i));
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
    }

    @Override
    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = 0L;
        }
    }

    public final static class ValuePadding {
        protected long p1, p2, p3, p4, p5, p6, p7;
        protected volatile long value = 0L;
        protected long p9, p10, p11, p12, p13, p14;
        protected long p15;
    }

    @Contended //实现对齐填充
    public final static class ValueNoPadding {
        // protected long p1, p2, p3, p4, p5, p6, p7;
        //8字节
        protected volatile long value = 0L;
        // protected long p9, p10, p11, p12, p13, p14, p15;
    }

}
