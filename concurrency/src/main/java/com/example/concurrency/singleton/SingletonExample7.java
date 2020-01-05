
package com.example.concurrency.singleton;

import com.example.concurrency.annoations.ThreadSafe;

/**
 * 使用枚举定义单利模式
 * 单例实例在类装载使用的时候进行创建
 */
@ThreadSafe
public class SingletonExample7 {
    // 私有构造
    public SingletonExample7() {
    }
    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getSingleton();
    }
    private enum  Singleton{
        INSTANCE;

        private SingletonExample7 singleton;
        /**
         * JVM 保证这个方法绝对只调用一次
         */
        Singleton() {
            singleton =  new SingletonExample7();
        }

        public SingletonExample7 getSingleton() {
            return singleton;
        }
    }
}
