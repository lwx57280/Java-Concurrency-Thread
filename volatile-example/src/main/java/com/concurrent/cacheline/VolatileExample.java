package com.concurrent.cacheline;

/**
 * @description: volatile 线程可见性
 * @author: cong zhi
 * @createDate: 2022/5/30 11:16
 * @updateUser: cong zhi
 * @updateDate: 2022/5/30 11:16
 * @updateRemark: 修改内容
 * @version: v1.0
 */
public class VolatileExample {

    public static volatile boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            int i = 0;
            // 活性失效
            while (!stop) {
                i++;
//                System.out.println(i);  // IO 操作
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        t1.start();
        System.out.println("begin start thread");
        Thread.sleep(1000);
        stop = true;
    }
}
