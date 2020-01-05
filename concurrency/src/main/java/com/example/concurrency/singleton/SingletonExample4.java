
package com.example.concurrency.singleton;

import com.example.concurrency.annoations.NotThreadSafe;

/**
 * 饿汉模式
 * 单例实例在第一次使用时进行创建
 */
@NotThreadSafe
public class SingletonExample4 {

    public SingletonExample4() {
    }
    // 单例对象
    private static SingletonExample4 instance = null;

    /**
     * CPU 指令
     * 1、 memory = allocate() 分配对象的内存空间
     * 2、ctorInstance() 初始化对象
     * 3、instance = memory 设置instance指向刚分配的内存
     *  单线程场景下实例化对象之后直接返回是没有问题，但是在多线程场景下就不一定，会发生指令重排序
     *
     *  JVM 和 CPU 优化，发生指令重排
     *  1、 memory = allocate() 分配对象的内存空间
     *  3、instance = memory 设置instance指向刚分配的内存
     *  2、ctorInstance() 初始化对象
     *
     * @return
     */
    public static  SingletonExample4 getInstance() {
        if (instance == null) { // 双重检测机制          // 线程B
            // 同步锁
            synchronized (SingletonExample4.class) {
                if (instance == null) {
                    instance = new SingletonExample4(); // 线程A
                }
            }
        }
        return instance;
    }
}
