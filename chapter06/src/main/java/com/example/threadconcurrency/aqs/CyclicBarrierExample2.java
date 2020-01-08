package com.example.threadconcurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 线程屏障
 */
@Slf4j
public class CyclicBarrierExample2 {
    private static CyclicBarrier barrier = new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            executor.execute(() -> {
                try {
                    race(threadNum);
                } catch (InterruptedException e) {
                    log.error("InterruptedException", e);
                }
            });
        }
        // 关掉不用的线程池
        executor.shutdown();
    }

    private static void race(int threadNum) throws InterruptedException {
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);
        try {
            barrier.await(2000, TimeUnit.MILLISECONDS);
        } catch (BrokenBarrierException | TimeoutException e) {
            log.warn("BrokenBarrierException | TimeoutException", e);
        }
        log.info("{} continue", threadNum);
    }
}
