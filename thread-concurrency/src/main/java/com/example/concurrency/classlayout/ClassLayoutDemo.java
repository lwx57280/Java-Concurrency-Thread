package com.example.concurrency.classlayout;


import org.openjdk.jol.info.ClassLayout;

public class ClassLayoutDemo {

    Object object=new Object();

    public static void main(String[] args) {

        // 构建了一个对象实例
        ClassLayoutDemo classLayoutDemo=new ClassLayoutDemo();
        System.out.println(ClassLayout.parseInstance(classLayoutDemo).toPrintable());
    }

}
