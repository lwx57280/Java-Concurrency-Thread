package com.example.concurrency.classlayout;


import org.openjdk.jol.info.ClassLayout;
/**
 * @description:    MarkWord对象头
 * @author:         cong zhi
 * @createDate:     2022/5/29 11:59
 * @updateUser:     cong zhi
 * @updateDate:     2022/5/29 11:59
 * @updateRemark:   修改内容
 * @version:        v1.0
 */
public class ClassLayoutDemo {

    Object object=new Object();

    public static void main(String[] args) {

        // 构建了一个对象实例
        ClassLayoutDemo classLayoutDemo=new ClassLayoutDemo();
        System.out.println(ClassLayout.parseInstance(classLayoutDemo).toPrintable());
    }

}
