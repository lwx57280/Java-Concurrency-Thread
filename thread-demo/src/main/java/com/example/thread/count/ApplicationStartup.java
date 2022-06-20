package com.example.thread.count;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ApplicationStartup {


    private static List<BaseHealthChecker> services;

    private static CountDownLatch countDownLatch = null;

    static {
        services = new ArrayList<>();
        services.add(new CacheHealthChecker(countDownLatch));
        services.add(new DataBaseHealthChecker(countDownLatch));
        countDownLatch = new CountDownLatch(services.size());
    }

    private final static ApplicationStartup INSTANCE = new ApplicationStartup();

    private ApplicationStartup() {
    }

    public static ApplicationStartup getInstance() {
        return INSTANCE;
    }

    public static boolean checkExternalService() throws InterruptedException {
        for (BaseHealthChecker bh : services) {
            new Thread(bh).start(); // 针对每个服务采用线程执行

        }
        countDownLatch.await();
        return true;
    }

}
