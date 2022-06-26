package com.example;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * @description:    动态修改核心线程数队列长度
 * @author:         cong zhi
 * @createDate:     2022/6/26 17:13
 * @updateUser:     cong zhi
 * @updateDate:     2022/6/26 17:13
 * @updateRemark:   修改内容
 * @version:        v1.0
 */
public class ThreadExcutorExample {


    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 6, 60
            , TimeUnit.SECONDS, new ResizeLinkedBlockQueue<Runnable>(30));

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(50000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        printPoolStatus("before");
        // 动态设置
        threadPoolExecutor.setCorePoolSize(4);
        threadPoolExecutor.setMaximumPoolSize(14);
        ResizeLinkedBlockQueue<Runnable> rb = (ResizeLinkedBlockQueue) threadPoolExecutor.getQueue();
        rb.setCapacity(100);
        printPoolStatus("after");
    }

    public static void printPoolStatus(String name) {
        ResizeLinkedBlockQueue<Runnable> rb = (ResizeLinkedBlockQueue) threadPoolExecutor.getQueue();
        System.out.println(name + "\n核心线程数量：" + threadPoolExecutor.getCorePoolSize() + "" +
                "最大线程数量:" + threadPoolExecutor.getMaximumPoolSize() + "" +
                "队列长度:" + (rb.size() + rb.remainingCapacity()));
    }
}
