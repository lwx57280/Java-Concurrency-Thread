package com.example.threadconcurrency.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class LockExample6 {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        // Condition多线程间协调通信
        Condition condition = reentrantLock.newCondition();
        new Thread(() -> {
            try {
                reentrantLock.lock();
                log.info("wait signal");    // 1、等待信号
                condition.await();
            } catch (InterruptedException e) {
                log.error("InterruptedException", e);
            }
            log.info("get signal");         // 4、得到信号
            reentrantLock.unlock();
        }).start();
        new Thread(() -> {
            reentrantLock.lock();
            log.info("get lock");           // 2、
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                log.error("InterruptedException", e);
            }
            condition.signalAll();
            log.info("send signal ");       // 3、发送信号
            reentrantLock.unlock();
        }).start();
    }
}
