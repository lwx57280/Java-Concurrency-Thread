package com.example.concurrency.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConditionDemoWait implements Runnable {

    private Lock lock;
    private Condition condition;

    public ConditionDemoWait(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        System.out.println("begin -ConditionDemoWait");
        lock.lock();
        try {
            condition.await();  // 让当前线程阻塞，Object.wait();
            System.out.println("end -ConditionDemoWait");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
