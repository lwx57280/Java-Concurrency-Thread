package com.example.thread.count;

import java.util.concurrent.CountDownLatch;

public abstract class BaseHealthChecker implements Runnable {
    /**
     * 服务名称
     */
    private String serviceName;
    private Boolean serviceUp;
    private CountDownLatch countDownLatch;

    public BaseHealthChecker(CountDownLatch countDownLatch, String serviceName) {
        this.countDownLatch = countDownLatch;
        this.serviceName = serviceName;
    }


    /**
     * 服务健康检测
     */
    public abstract void verifyService() throws Exception;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public void run() {
        try {
            verifyService();
            serviceUp = true;
        } catch (Exception e) {
            serviceUp = false;
        } finally {
            if (countDownLatch != null) {
                // 释放
                countDownLatch.countDown();
            }
        }
    }

    public Boolean getServiceUp() {
        return serviceUp;
    }

    public void setServiceUp(Boolean serviceUp) {
        this.serviceUp = serviceUp;
    }
}
