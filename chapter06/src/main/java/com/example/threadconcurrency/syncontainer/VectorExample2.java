package com.example.threadconcurrency.syncontainer;

import com.example.threadconcurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Vector;

@Slf4j
@ThreadSafe
public class VectorExample2 {
    // vector是线程同步容器
    private static List<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            vector.add(i);
        }
        new Thread(() -> {
            for (int i = 0; i < vector.size(); i++) {
                Integer num = vector.remove(i);
                log.info("线程1 || num :{}",(num));
            }
        }, "线程1").start();
        new Thread(() -> {
            for (int i = 0; i < vector.size(); i++) {
                log.info("线程2 || i:{}",vector.get(i));
                vector.get(i);
            }
        }, "线程2").start();
        log.info("size:{}",vector.size());
    }
}
