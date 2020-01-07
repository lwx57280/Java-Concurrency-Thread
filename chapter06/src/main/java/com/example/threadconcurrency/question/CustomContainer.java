package com.example.threadconcurrency.question;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 使用wait和notify实现
 * 实现一个容器，提供两个方法，一个size,一个add
 * 写两个线程，线程1往自定义容器中添加十个元素，线程2实时监控容器中的数量，
 * 在容器中元素个数为5的时候输出并结束线程
 * 使用门闩，门闩初始为1，当变为0的时候门闩打开，线程2就受到了通知，输出并结束
 */
public class CustomContainer {

    volatile List<Object> list = Lists.newArrayList();

    private static final int TOTAL = 10;

    public void add(Object obj) {
        list.add(obj);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final CustomContainer customContainer = new CustomContainer();
        new Thread(() -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "- end");
        }, "线程2").start();
        new Thread(() -> {
            for (int i = 0; i < TOTAL; i++) {
                if (customContainer.size() == 5) {
                    countDownLatch.countDown();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                customContainer.add(new Object());
                System.out.println(Thread.currentThread().getName() + "- add");
            }
        }, "线程1").start();
    }
}
