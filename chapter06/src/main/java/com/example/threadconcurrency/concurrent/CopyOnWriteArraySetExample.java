package com.example.threadconcurrency.concurrent;

import com.example.threadconcurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.*;

@Slf4j
@ThreadSafe
public class CopyOnWriteArraySetExample {

    /**
     * 请求总数
     */
    private static int clientTotal = 5000;
    // 同时并发执行线程数
    private static int threadTotal = 200;
    // CopyOnWriteArraySet线程安全
    private static Set<Integer> set = new CopyOnWriteArraySet<>();

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
        log.info("set size:{}",set.size());
    }
    private static void update(int i) {
        set.add(i);
    }
}
