package com.example.countdowan;

import java.util.concurrent.CountDownLatch;

public class CountDownExample {

    static CountDownLatch countDownLatch = new CountDownLatch(2);

    static class Thread1 extends Thread{

        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 表示我已经干完了
            countDownLatch.countDown();

        }
    }

    static class Thread2 extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread1 t1= new Thread1();
        t1.start();
        Thread2 t2=new Thread2();
        t2.start();
        // 阻塞线程
        countDownLatch.await();

        System.out.println("所有线程执行结束");
    }
}
