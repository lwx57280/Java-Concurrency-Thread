package com.example;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DynamicExample {


    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2
            , 6, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.execute(()->{
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        printPollStatus("before");
        // 动态设置
        threadPoolExecutor.setCorePoolSize(4);   // 存放在配置中心
        threadPoolExecutor.setMaximumPoolSize(14);
        printPollStatus("after");
    }

    private static void printPollStatus(String before) {
        System.out.println("核心线程数量:" + threadPoolExecutor.getCorePoolSize() + ""
                + "最大线程数量:" + threadPoolExecutor.getMaximumPoolSize());

    }
}
