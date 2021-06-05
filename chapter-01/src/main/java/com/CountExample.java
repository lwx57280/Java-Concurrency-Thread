package com;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.logging.Logger;


public class CountExample {

    private final static Logger logger = Logger.getLogger("chapter07");

    private static int threadTotal = 1;
    private static int clientTotal = 5000;

    private static long count = 0;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        for (int index = 0; index < clientTotal; index++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    logger.info("Exception" + e.getMessage());
                }
            });
        }
        executorService.shutdown();
        logger.info("count= " + count);

    }

    private static void add() {
        count++;
    }
}
