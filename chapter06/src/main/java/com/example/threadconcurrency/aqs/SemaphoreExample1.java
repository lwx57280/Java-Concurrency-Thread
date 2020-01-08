package com.example.threadconcurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量控制（可以很容易的控制某个资源可被同时访问的个数）
 */
@Slf4j
public class SemaphoreExample1 {
    private static int threadCount = 20;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 允许的并发数
        final Semaphore semaphore = new Semaphore(3);
            for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();    // 获取一个许可
                    test(threadNum);
                    semaphore.release();    // 释放一个许可
                } catch (InterruptedException e) {
                    log.error("InterruptedException", e);
                }
            });
        }
        log.info("finish!");
        executorService.shutdown();
    }
    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(1000);
        log.info("{}", threadNum);
    }

}
