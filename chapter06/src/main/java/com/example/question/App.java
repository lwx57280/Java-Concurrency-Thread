package com.example.question;

import java.lang.reflect.Field;

/**
 * 两个Integer的引用对象传递给一个swap方法在方法内部进行交换，
 * 返回后，两个引用的值是否发送变化？
 */
public class App {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Integer a = 1, b = 2;
        System.out.println(a == b);
        System.out.println("before:a=" + a + ",b=" + b);
        swap(a, b);
        System.out.println("after:a=" + a + ",b=" + b);
    }
    private static void swap(Integer value1, Integer value2) throws IllegalAccessException, NoSuchFieldException {
        /**
         * 涉及Java知识点
         * 1、Integer -128到127之间缓存。会有内存缓存，缓存修改的是同一块内存
         * 2、自动拆箱、装箱
         * 3、利用Java反射 、通过Field反射拿到private final value 成员变量
         *    要去修改私有成员变量，需要通过setAccessible(true)方法可以去访问私有成员变量
         *    改变value的值是它的内存地址
         */
//        Integer temp =value1;
//        value1=value2;
//        value2=temp;
        Field field = Integer.class.getDeclaredField("value");
        field.setAccessible(true);
        Integer temp = new Integer(value1.intValue());     // new Integer(100) 每次都会新建一个对象；且该构造函数在jdk1.9 已被废弃
//        Integer temp =Integer.valueOf(value1.intValue());// Integer.valueOf(100) 会使用缓存池中的对象，多次调用会取得同一个对象的引用。
        field.set(value1, value2.intValue());   //  set 入参是Object ,所以传递的是Integer.valueOf(value1.iniValue).intValue()
        field.set(value2, temp);
    }
}
