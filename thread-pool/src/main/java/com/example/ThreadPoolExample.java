package com.example;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * @description:    线程池案例
 * 4个线程去执行10个任务，必然会有一些线程去重复执行一些任务
 * @author:         cong zhi
 * @createDate:     2022/6/26 11:04
 * @updateUser:     cong zhi
 * @updateDate:     2022/6/26 11:04
 * @updateRemark:   修改内容
 * @version:        v1.0
 */
public class ThreadPoolExample {

    public static void main(String[] args) {
        // 线程池中只有4个线程（4个线程去执行10个任务，必然会有一些线程去重复执行一些任务）
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            // 把一个实现了Runnable接口的任务给到线程池执行
            executorService.execute(new Task()); // 异步执行传递进去task

        }
        executorService.shutdown(); // 关闭线程池

    }

    static class Task implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+" - 开始执行任务");
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" - 执行完成");
        }
    }
}
