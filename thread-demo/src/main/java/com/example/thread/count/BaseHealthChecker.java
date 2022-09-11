package com.example.thread.count;

public abstract class BaseHealthChecker implements Runnable {
    /**
     * 服务名称
     */
    private String serviceName;
    private Boolean serviceUp;


    public BaseHealthChecker(String serviceName) {
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
        }
    }

    public Boolean isServiceUp() {
        return serviceUp;
    }

    public void setServiceUp(Boolean serviceUp) {
        this.serviceUp = serviceUp;
    }
}
