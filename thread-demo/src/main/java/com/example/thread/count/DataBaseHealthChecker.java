package com.example.thread.count;

import java.util.concurrent.CountDownLatch;

public class DataBaseHealthChecker extends BaseHealthChecker {

    private CountDownLatch countDownLatch;

    public DataBaseHealthChecker(CountDownLatch countDownLatch) {
        super("DataBaseHealthChecker");
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void verifyService() throws Exception {
        System.out.println("Checking:" + this.getServiceName());
        try {
            Thread.sleep(300);

        } catch (Exception e) {
            throw e;
        }
        // 释放
        countDownLatch.countDown();
        System.out.println(this.getServiceName() + "健康状态正常！");
    }


}
