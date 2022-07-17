package com.example.thread;

import java.util.concurrent.TimeUnit;

/**
 * @description: 线程状态
 * @author: cong zhi
 * @createDate: 2022/7/17 17:28
 * @updateUser: cong zhi
 * @updateDate: 2022/7/17 17:28
 * @updateRemark: 修改内容
 * @version: v1.0
 */
public class ThreadStatus {

    public static void main(String[] args) {
        // TIME_WAITING
        new Thread(()-> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(100);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"timewwaiting").start();
        //WAITING，线程在ThreadStatus类锁上通过wait进行等待
        new Thread(()-> {
            while (true) {
                synchronized (ThreadStatus.class) {
                    try {
                        ThreadStatus.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"Waiting").start();
        //线程在ThreadStatus加锁后，不会释放锁
        new Thread(new BlockedDemo(),"BlockedDemo-01").start();
        new Thread(new BlockedDemo(),"BlockedDemo-02").start();
    }

    static class BlockedDemo extends Thread {
        @Override
        public void run() {
            synchronized (BlockedDemo.currentThread()){
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
