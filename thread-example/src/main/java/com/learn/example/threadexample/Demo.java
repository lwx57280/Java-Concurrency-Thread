package com.learn.example.threadexample;


import org.openjdk.jol.info.ClassLayout;

public class Demo {

//    Object o = new Object();

    public static void main(String[] args) {
        Demo demo=new Demo();
        System.out.println(ClassLayout.parseInstance(demo).toPrintable());
    }

}
