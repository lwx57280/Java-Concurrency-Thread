package com.example;

import lombok.SneakyThrows;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ThreadExample implements Runnable {

    private static BlockingQueue<Object> tasks = new LinkedBlockingDeque<>();


    @SneakyThrows
    @Override
    public void run() { // 启动start()之后，会回调run()方法
        while (!Thread.currentThread().isInterrupted()) {
            // 生产者——消费者模型

            Object task = tasks.take(); // 阻塞方法
            ((Runnable) task).run();
        }
    }

    public static void main(String[] args) {

    }
}
