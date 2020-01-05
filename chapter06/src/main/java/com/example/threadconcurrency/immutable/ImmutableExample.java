package com.example.threadconcurrency.immutable;

import com.example.threadconcurrency.annoations.NotThreadSafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 不可变对象
 *
 * 不可变对象需要满足的条件
 *
 * 	Ø 对象创建以后其状态就不能修改。
 * 	Ø 对象所有域都是final类型。
 * 	Ø 对象是正确创建的（在对象创建期间，this引用没有逸出）
 * final关键字：类、方法、变量
 * 修饰类：不能被继承
 * 修饰方法：1、锁定方法不被继承类修改；2、效率
 * 修饰变量：基本数据类型变量，引用类型变量
 */
@Slf4j
@NotThreadSafe
public class ImmutableExample {

    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
        map.put(1, 3);
        log.info("{}",map.get(1));
    }

}
