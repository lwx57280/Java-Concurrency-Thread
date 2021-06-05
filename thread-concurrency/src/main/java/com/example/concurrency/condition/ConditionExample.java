package com.example.concurrency.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        ConditionDemoWait cdw = new ConditionDemoWait(lock, condition);
        ConditionDemoNotify cdn = new ConditionDemoNotify(lock, condition);
        new Thread(cdw).start();
        new Thread(cdn).start();
    }
}
