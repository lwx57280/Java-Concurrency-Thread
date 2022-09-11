package com.example.thread.count;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ApplicationStartup {


    private static List<BaseHealthChecker> services;

    private static CountDownLatch countDownLatch = new CountDownLatch(2);

    static {
        services = new ArrayList<BaseHealthChecker>();
        services.add(new CacheHealthChecker(countDownLatch));
        services.add(new DataBaseHealthChecker(countDownLatch));
    }

    private final static ApplicationStartup INSTANCE = new ApplicationStartup();

    private ApplicationStartup() {
    }

    public static ApplicationStartup getInstance() {
        return INSTANCE;
    }

    public static boolean checkExternalService() throws InterruptedException {
        for (final BaseHealthChecker v : services) {
            //  new Thread(v).start(); // 针对每个服务采用线程执行
            if (v.isServiceUp()) {
                return false;
            }

        }
        countDownLatch.await();
        return true;
    }

}
