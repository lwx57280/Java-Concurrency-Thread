package com.learn.example.threadexample;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionMain {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        ConditionDemoWait cd = new ConditionDemoWait(lock, condition);
        ConditionDemoWait cd2 = new ConditionDemoWait(lock, condition);

        ConditionDemoSignal cds = new ConditionDemoSignal(lock, condition);
        new Thread(cd, "thread1").start();
        new Thread(cd2, "thread2").start();

        new Thread(cds, "thread3").start();
    }
}
