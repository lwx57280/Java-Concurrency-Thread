
package com.example.concurrency.singleton;

import com.example.concurrency.annoations.ThreadSafe;

/**
 * 饿汉模式(线程安全)
 * 单例实例在类装载使用的时候进行创建
 */
@ThreadSafe
public class SingletonExample2 {

    public SingletonExample2() {
    }

    // 单例对象
    private static SingletonExample2 instance = new SingletonExample2();

    public static SingletonExample2 getInstance() {
        return instance;
    }
}
