package com.example.threadconcurrency.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 */
@Slf4j
public class ThreadPoolExample4 {

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        // 延迟3秒后执行
       // executor.schedule(() -> log.warn("schedule run"),3, TimeUnit.SECONDS);
        // 延迟1秒后，每隔3秒执行一次
        executor.scheduleAtFixedRate(() -> log.warn("schedule run"), 1, 3, TimeUnit.SECONDS);
        //executor.shutdown();
    }

}
