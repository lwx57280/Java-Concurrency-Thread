package com.example.concurrency.classlayout;

import org.openjdk.jol.info.ClassLayout;

/**
 * @description: 线程轻量级锁
 * @author: cong zhi
 * @createDate: 2022/5/29 17:33
 * @updateUser: cong zhi
 * @updateDate: 2022/5/29 17:33
 * @updateRemark: 修改内容
 * @version: v1.0
 */
public class LockDemo {
    Object o = new Object();
    public static void main(String[] args) {
        LockDemo demo = new LockDemo();
        // 加锁之前
        System.out.println(ClassLayout.parseInstance(demo).toPrintable());
        System.out.println("加锁之后");
        // 如果有其他线程进入到下面的同步块，则先自旋
        // CAS() 保证数据操作的原子性
        synchronized (demo) {
            System.out.println(ClassLayout.parseInstance(demo).toPrintable());
        }
    }
}
