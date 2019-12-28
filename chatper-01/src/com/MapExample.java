package com;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.logging.Logger;

public class MapExample {

    private final static Logger logger = Logger.getLogger("MapExample");

    private static Map<Integer, Integer> map = new HashMap<Integer, Integer>();

    private static int threadNum = 200;
    private static int clientNum = 5000;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(threadNum);
        for (int index = 0; index < clientNum; index++) {
            final int threadNum = index;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    func(threadNum);
                    semaphore.release();
                } catch (Exception e) {
                    logger.info("Exception" + e.getMessage());
                }
            });
        }
        executorService.shutdown();
        logger.info("size{} " + map.size());
    }

    private static void func(int threadNum) {
        map.put(threadNum, threadNum);
    }
}
