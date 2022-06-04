package com.example;

import org.openjdk.jol.info.ClassLayout;

public class LockDemo {

    Object o =new Object();

    public static void main(String[] args) {
        LockDemo demo=new LockDemo();
        // 加锁之前
        System.out.println(ClassLayout.parseInstance(demo).toPrintable());
        // 加锁之后
        System.out.println("加锁之后.....");
        synchronized (demo) {
            System.out.println(ClassLayout.parseInstance(demo).toPrintable());
        }

    }
}
