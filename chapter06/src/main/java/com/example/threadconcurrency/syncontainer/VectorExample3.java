package com.example.threadconcurrency.syncontainer;

import com.example.threadconcurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

@Slf4j
@ThreadSafe
public class VectorExample3 {
    // vector是线程同步容器
    private static Vector<Integer> vector = new Vector<>();
    // java.util.ConcurrentModificationException
    private static void test1(Vector<Integer> v1) {
        for (Integer i : v1) {
            v1.remove(i);
        }
    }
    // java.util.ConcurrentModificationException
    private static void test2(Vector<Integer> v2) {
        Iterator<Integer> iterator =v2.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (i.equals(3)) {
                v2.remove(i);
            }
        }
    }
    // success
    private static void test3(Vector<Integer> v3) {
        for (int i = 0; i < v3.size(); i++) {
            if (v3.get(i).equals(3)) {
                v3.remove(i);
            }
        }
    }
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            vector.add(i);
        }
        test1(vector);

    }
}
