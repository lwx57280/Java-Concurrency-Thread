package com.learn.example.countdownlath;

import java.util.concurrent.CountDownLatch;
/**
 * 线程阻塞  CountDownLatch基本使用案例
 * 当计数器为0时，唤醒await() 线程
 * @author:         cong zhi
 * @createDate:     2022/9/11 14:12
 * @updateUser:     cong zhi
 * @updateDate:     2022/9/11 14:12
 * @updateRemark:   修改内容
 * @version:        v1.0
 */
public class MainExample {


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":开始执行");
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + ":执行结束");
        },"t1").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":开始执行");
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + ":执行结束");
        },"t2").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":开始执行");
            countDownLatch.countDown();  // 针对初始化数据3,进行递增
            System.out.println(Thread.currentThread().getName() + ":执行结束");
        },"t3").start();
        countDownLatch.await(); // 让当前main线程阻塞，知道默认递增的数值变成0以后，被唤醒
        System.out.println("等待上面三个任务执行结束之后，再执行当前的指令。");
    }
}
