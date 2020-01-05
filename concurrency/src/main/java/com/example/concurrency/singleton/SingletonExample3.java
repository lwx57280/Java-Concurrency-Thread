
package com.example.concurrency.singleton;

import com.example.concurrency.annoations.NotRecommend;
import com.example.concurrency.annoations.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类装载使用的时候进行创建
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {

    public SingletonExample3() {
    }

    // 单例对象
    private static SingletonExample3 instance = null;

    public static synchronized SingletonExample3 getInstance() {
        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }
}
