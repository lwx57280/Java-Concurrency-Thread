package com.example.thread.count;

import java.util.concurrent.CountDownLatch;

public class DataBaseHealthChecker extends BaseHealthChecker {

    public DataBaseHealthChecker(CountDownLatch countDownLatch) {
        super(countDownLatch, "DataBaseHealthChecker");
    }

    @Override
    public void verifyService() throws Exception {
        System.out.println("Checking:" + this.getServiceName());
        try {
            Thread.sleep(300);

        } catch (Exception e) {
            throw e;
        }
        System.out.println(this.getServiceName() + "健康状态正常！");
    }
}
