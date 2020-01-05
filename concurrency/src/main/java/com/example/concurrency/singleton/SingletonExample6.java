
package com.example.concurrency.singleton;

import com.example.concurrency.annoations.ThreadSafe;

/**
 * 饿汉模式(线程安全) 另外一种写法
 * 单例实例在类装载使用的时候进行创建
 */
@ThreadSafe
public class SingletonExample6 {

    public SingletonExample6() {
    }

    // 单例对象(注意顺序)
    private static SingletonExample6 instance = null;
    static {
        instance = new SingletonExample6();
    }


    public static SingletonExample6 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }
}
