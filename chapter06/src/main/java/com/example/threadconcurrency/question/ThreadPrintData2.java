package com.example.threadconcurrency.question;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 4）线程A不断打印1-10的数字，打印到第5个数字时通知线程B，请完成编码。
 * CountDownLatch如何工作？
 * CountDownLatch是通过一个计数器来实现的，计数器的初始值为线程的数量。每当一个线程完成了自己的任务后，计数器的值就会减1。
 * 当计数器值到达0时，它表示所有的线程已经完成了任务，然后在闭锁上等待的线程就可以恢复执行任务。
 * <p>
 * 总结一下，
 * 　　1、CountDownLatch end = new CountDownLatch(N); //构造对象时候 需要传入参数N
 * <p>
 * 　　2、end.await()  能够阻塞线程 直到调用N次end.countDown() 方法才释放线程
 * <p>
 * 　　3、end.countDown() 可以在多个线程中调用  计算调用次数是所有线程调用次数的总和
 */
public class ThreadPrintData2 {

    private static AtomicInteger num = new AtomicInteger(0);
    private static final int TOTAL = 10;


    public static void main(String[] args) throws InterruptedException {
        // 构造函数中添加了参数2，就需要调用 2 次 countDown()  才能将 end.await() 阻塞的线程唤醒。
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        long start = System.currentTimeMillis();
        new Thread(() -> {
            while (num.get() < TOTAL) {
                synchronized (num) {
                    System.out.println(Thread.currentThread().getName() + "打印:" + num.incrementAndGet());
                    if (num.get() == 5) {
                        try {
                            num.notify();
                            num.wait();     // 阻塞当前线程，释放锁
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            // 每调用一次这个方法，在构造函数中初始化的count值就减1。所以当N个线程都调 用了这个方法，
                            // count的值等于0，然后主线程就能通过await()方法，恢复执行自己的任务。
                            countDownLatch.countDown();
                        }
                    }
                }
            }
            System.out.println("线程A执行完了");
        }, "线程A").start();
        new Thread(() -> {
            while (num.get() < TOTAL) {
                synchronized (num) {
                    if (num.get() < 5) {
                        try {
                            num.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else if (num.get() == 9) {
                        num.notify();       // 唤醒其他等锁对象
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
