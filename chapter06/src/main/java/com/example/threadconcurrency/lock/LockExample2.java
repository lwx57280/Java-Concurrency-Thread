package com.example.threadconcurrency.lock;


import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantLock 可重入锁
 * 在读和写分别加lock锁
 */
@Slf4j
public class LockExample2 {
    /**
     * 请求总数
     */
    private static int clientTotal = 5000;
    // 同时并发执行线程数
    private static int threadTotal = 200;

    private final Map<String, Data> map = Maps.newTreeMap();

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public Data get(String key) {
        readLock.lock();
        try {
            return map.get(key);
        }finally {
            readLock.unlock();
        }
    }

    public Set<String> getAllKeys() {
        readLock.lock();
        try{
            return map.keySet();
        }finally {
            readLock.unlock();
        }
    }

    public Data put(String key, Data value) {
        writeLock.lock();
        try{
            return map.put(key, value);
        }finally {
            readLock.unlock();
        }
    }

    class Data{

    }
}

