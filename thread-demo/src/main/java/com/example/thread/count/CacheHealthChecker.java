package com.example.thread.count;

import java.util.concurrent.CountDownLatch;
/**
 * @description:    缓存健康检查
 * @author:         cong zhi
 * @createDate:     2022/6/20 15:31
 * @updateUser:     cong zhi
 * @updateDate:     2022/6/20 15:31
 * @updateRemark:   修改内容
 * @version:        v1.0
 */
public class CacheHealthChecker extends BaseHealthChecker {

    private CountDownLatch countDownLatch;

    public CacheHealthChecker(CountDownLatch countDownLatch) {
        super("CacheHealthChecker");
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void verifyService() throws Exception{
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
