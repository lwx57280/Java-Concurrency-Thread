package com.example.thread;

/**
 * synchroinzed 锁特性
 * @author:         cong zhi
 * @createDate:     2022/9/17 15:59
 * @updateUser:     cong zhi
 * @updateDate:     2022/9/17 15:59
 * @updateRemark:   修改内容
 * @version:        v1.0
 */
public class SynchronizedDemo {


    public static void main(String[] args) throws InterruptedException {
        final Object obj = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (obj) {
                try {
                    obj.wait();
                    System.out.println("Thread 1 wake up");
                } catch (InterruptedException e) {
                }
            }
        });
        t1.start();
        Thread.sleep(1000);
        Thread t2 = new Thread(() -> {
            synchronized(obj) {
                obj.notify();
                System.out.println("Thread 2 sent notfiy");
            }
        });
        t2.start();
    }

}
