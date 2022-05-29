package com.example;

import org.openjdk.jol.info.ClassLayout;

/**
 * @description: 重量级锁
 * @author: cong zhi
 * @createDate: 2022/5/29 18:49
 * @updateUser: cong zhi
 * @updateDate: 2022/5/29 18:49
 * @updateRemark: 修改内容
 * @version: v1.0
 */
public class HeightLockDemo {

    public static void main(String[] args) {
        HeightLockDemo demo = new HeightLockDemo();
        Thread t1 = new Thread(() -> {
            synchronized (demo) {
                System.out.println("t1 locked");
                System.out.println(ClassLayout.parseInstance(demo).toPrintable());
            }
        });
        t1.start();
        synchronized (demo) {
            System.out.println("main locked");
            System.out.println(ClassLayout.parseInstance(demo).toPrintable());
        }
    }
}
