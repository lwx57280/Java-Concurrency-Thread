package com.example.threadconcurrency.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * ThreadLocal线程隔离机制
 * @author:         cong zhi
 * @createDate:     2022/9/12 11:06
 * @updateUser:     cong zhi
 * @updateDate:     2022/9/12 11:06
 * @updateRemark:   修改内容
 * @version:        v1.0
 */
public class ThreadPoolThreadLocalDemo {


    private static ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
    private static ExecutorService executorService = Executors.newFixedThreadPool(1);

    public static void main(String[] args) {
        System.out.println("开始执行Main方法");

        threadLocal.set("Main-value");
        executorService.execute(() -> {
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
        });
        threadLocal.set("Update-Main-value");
        executorService.execute(() -> {
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
        });
    }
}

