package com.learn.example.threadexample;

import org.openjdk.jol.info.ClassLayout;

/**
 * 对象加锁之前和加锁之后
 *
 * @author: cong zhi
 * @createDate: 2022/7/31 10:03
 * @updateUser: cong zhi
 * @updateDate: 2022/7/31 10:03
 * @updateRemark: 修改内容
 * @version: v1.0
 */
public class LockExample {

    public static void main(String[] args) {

        LockExample le = new LockExample();

        Thread t1 = new Thread(() -> {
            System.out.println("t1 get Lock");
            synchronized (le) {
                System.out.println(ClassLayout.parseInstance(le).toPrintable());
            }
        });
        t1.start();

        synchronized (le) {
            System.out.println("main get Lock");
            System.out.println(ClassLayout.parseInstance(le).toPrintable());

        }
    }
}
