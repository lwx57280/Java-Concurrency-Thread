package com.example.thread.count;

import java.util.concurrent.CountDownLatch;
/**
 * @description:    反过来使用
 * @author:         cong zhi
 * @createDate:     2022/6/20 16:06
 * @updateUser:     cong zhi
 * @updateDate:     2022/6/20 16:06
 * @updateRemark:   修改内容
 * @version:        v1.0
 */
public class CountDownExample {

    static CountDownLatch countDownLatch = new CountDownLatch(1);

    static class Thread1 extends Thread{

        @Override
        public void run() {
            try {
                Thread.sleep(500);
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Thread2 extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(500);
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    static class Thread3 extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(500);
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread1 t1= new Thread1();
        t1.start();
        Thread2 t2=new Thread2();
        t2.start();

        Thread3 t3=new Thread3();
        t3.start();


        System.out.println("所有线程执行结束");
        // 表示我已经干完了
        countDownLatch.countDown();
    }
}
