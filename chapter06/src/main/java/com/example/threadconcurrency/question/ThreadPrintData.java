package com.example.threadconcurrency.question;

import java.util.concurrent.CountDownLatch;

/**
 * 两个线程分别打印0-100的之间的奇偶数
 */
public class ThreadPrintData {
    private static CountDownLatch countDownLatch = new CountDownLatch(2);
    private static Object lock = new Object();

    private static volatile Integer i = 0;
    private static final int TOTAL = 100;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        new Thread(() -> {
            while (i <= TOTAL) {
                synchronized (lock) {
                    if (i % 2 == 1) {
                        System.out.println(Thread.currentThread().getName() + "打印:" + i++);
                        lock.notifyAll();
                    }else {
                        if (i <= TOTAL) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
            countDownLatch.countDown();
        }, "奇数线程").start();
        new Thread(()->{
            while (i <= TOTAL) {
                synchronized (lock) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName()+ "打印:" + i++);
                        // 线程调用了对象的 notifyAll()方法（唤醒所有 wait 线程）
                        lock.notifyAll();
                    }else {
                        if (i <= TOTAL) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
            countDownLatch.countDown();
        },"偶数线程").start();
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("共耗时:"+(end-start) + "ms");
    }
}
