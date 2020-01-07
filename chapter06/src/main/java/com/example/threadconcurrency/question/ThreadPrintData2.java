package com.example.threadconcurrency.question;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 4）线程A不断打印1-10的数字，打印到第5个数字时通知线程B，请完成编码。
 */
public class ThreadPrintData2 {

    private static AtomicInteger num = new AtomicInteger(0);
    private static final int TOTAL = 10;
    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        long start = System.currentTimeMillis();
        new Thread(() -> {
            while (num.get() < TOTAL) {
                synchronized (num) {
                    System.out.println(Thread.currentThread().getName() + "打印:" + num.incrementAndGet());
                    if (num.get() == 5) {
                        try {
                            num.wait();
                            num.notify();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            countDownLatch.countDown();
            System.out.println("线程A执行完了");
        }, "线程A").start();
        new Thread(() -> {
            while (num.get() < TOTAL) {
                synchronized (num) {
                    if (num.get() <5) {
                        try {
                            num.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else if (num.get() == 9) {
                        num.notify();
                    }
                    System.out.println(Thread.currentThread().getName() + "打印:" + num.incrementAndGet());
                }
            }
            countDownLatch.countDown();
            System.out.println("线程B执行完了");
        }, "线程B").start();
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("共耗时:" + (end - start) + "ms");
    }
}
