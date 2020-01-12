package com.example.threadconcurrency.deadlock;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程死锁
 * 避免死锁的几个常见方法：
 * 1、避免一个线程同时获取多个锁
 * 2、避免一个线程在锁内同时占用多个资源，尽量保证每个锁只占用一个资源
 * 3、尝试使用定时锁、使用lock.tryLock(timeout)来替代使用内部锁机制
 * 4、对于数据库锁、加锁和解锁必须在一个数据库连接里，否则会出现解锁失败的情况
 */
@Slf4j
public class DeadLockDemo {
    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) {
        new DeadLockDemo().deadLock();
    }

    private void deadLock() {
        new Thread(() -> {
            synchronized (A) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                    log.info("1");
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (B) {
                synchronized (A) {
                    log.info("2");
                }
            }
        }).start();
    }
}
