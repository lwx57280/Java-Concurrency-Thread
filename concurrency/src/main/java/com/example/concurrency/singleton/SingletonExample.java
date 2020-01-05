
package com.example.concurrency.singleton;

import com.example.concurrency.annoations.NotThreadSafe;

/**
 * 懒汉模式
 */
@NotThreadSafe
public class SingletonExample {

    public SingletonExample() {
    }
    // 单例对象
    private static SingletonExample instance =null;

    public static SingletonExample getInstance() {
        if (instance == null) {
            instance = new SingletonExample();
        }
        return instance;
    }
}
