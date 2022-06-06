package com.example.concurrency.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        try {
            lock.lock();
            // N个线程被await
            lock.newCondition().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
        lock.newCondition().signal();
        System.out.println("Hello World!");

    }
}
