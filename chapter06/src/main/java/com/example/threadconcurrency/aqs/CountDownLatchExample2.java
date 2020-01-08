package com.example.threadconcurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CountDownLatchExample2 {
    private static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    log.error("InterruptedException", e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await(10, TimeUnit.MICROSECONDS);
        log.info("finish!");
        // 如果调用shutdown方法，并不是第一时间将所有线程都全部销毁，而是会让当前已有的线程执行完
        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException{
        Thread.sleep(100);
        log.info("{}", threadNum);
    }

}
