package com.example.threadconcurrency.commonUnsafe;

import com.example.threadconcurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@ThreadSafe
public class DataFormatExample2 {
    /**
     * 请求总数
     */
    private static int clientTotal = 5000;
    // 同时并发执行线程数
    private static int threadTotal = 200;


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int index = 0; index < clientTotal; index++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update();
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
    }

    private static void update() {
        try {
            // 对象封闭的方式，声明局部变量，每次声明一个新对象来使用
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            simpleDateFormat.parse("20200101");
        } catch (ParseException e) {
            log.error("parse ParseException",e);
        }
    }
}
