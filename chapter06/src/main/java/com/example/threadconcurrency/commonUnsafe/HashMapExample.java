package com.example.threadconcurrency.commonUnsafe;

import com.example.threadconcurrency.annoations.NotThreadSafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe
public class HashMapExample {

    /**
     * 请求总数
     */
    private static int clientTotal = 5000;
    // 同时并发执行线程数
    private static int threadTotal = 200;
    // HashMap 多线程操作不安全
    private static Map<Integer,Integer> map = Maps.newHashMap();
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int index = 0; index < clientTotal; index++) {
            final int count = index;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                } catch (Exception e) {
                    log.error("Exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        // 线程执行完之后，如果这个线程池不再使用需要关闭
        executorService.shutdown();
        log.info("map size:{}",map.size());
    }
    private static void update(int i) {
        map.put(i, i);
    }
}
