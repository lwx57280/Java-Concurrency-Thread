package com.example;

import lombok.SneakyThrows;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
/**
 * 线程复用
 * @author:         cong zhi
 * @createDate:     2022/9/12 15:07
 * @updateUser:     cong zhi
 * @updateDate:     2022/9/12 15:07
 * @updateRemark:   修改内容
 * @version:        v1.0
 */
public class ThreadExample implements Runnable {
    // 阻塞队列
    private static BlockingQueue<String> tasks = new LinkedBlockingDeque<>();


    static {
        // 启动一个消费者线程
        new TaskConsumer("Consumer-Task").start();
    }

    /**
     * 消费者线程
     */
    static class TaskConsumer extends Thread {

        public TaskConsumer() {
        }

        public TaskConsumer(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    String task = tasks.take(); // 从阻塞队列中获取任务，如果队列为空，则阻塞
                    System.out.println(Thread.currentThread().getName() + ": 接受到一个任务:" + task);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @SneakyThrows
    @Override
    public void run() { // 启动start()之后，会回调run()方法
        while (!Thread.currentThread().isInterrupted()) {
            // 生产者——消费者模型

            Object task = tasks.take(); // 阻塞方法
            ((Runnable) task).run();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 生产者进行生产数据
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要处理的任务：");
        while (true) {
            String task = scanner.nextLine();
            tasks.put(task);
        }
    }
}
