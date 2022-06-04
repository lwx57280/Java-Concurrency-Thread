package com.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: lock 锁的获取和释放需要手动释放
 * @author: cong zhi
 * @createDate: 2022/6/1 14:23
 * @updateUser: cong zhi
 * @updateDate: 2022/6/1 14:23
 * @updateRemark: 修改内容
 * @version: v1.0
 */
public class LockExample {

    static Lock lock = new ReentrantLock();

    private static int count = 0;

    private static void inc() {
        lock.lock(); // 抢占锁，如果没有抢占到锁，会阻塞
        try {
            Thread.sleep(1);
            count++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> LockExample.inc()).start();
        }
        Thread.sleep(3000);
        System.out.println("result:" + count);
    }
}
