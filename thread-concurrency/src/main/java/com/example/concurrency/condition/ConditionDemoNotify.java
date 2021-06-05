package com.example.concurrency.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConditionDemoNotify implements Runnable {

    private Lock lock;
    private Condition condition;

    public ConditionDemoNotify(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        System.out.println("begin - ConditionDemoNotify");
        lock.lock();  // 相当于synchronized(lock)
        try {
            condition.signal(); // 让当前线程唤醒,Object.notify(); 因为任何对象会有monitor
            System.out.println("end -ConditionDemoNotify");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
