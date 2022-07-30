package com.learn.example.threadexample;

/**
 * @description: java类作用描述
 * @author: cong zhi
 * @createDate: 2022/7/24 18:52
 * @updateUser: cong zhi
 * @updateDate: 2022/7/24 18:52
 * @updateRemark: 修改内容
 * @version: v1.0
 */
public class AtomicExample {

    int i = 0;

    public void incr() {
        // 只需要包裹存在线程安全问题的代码
        synchronized (this) {
            i++;
        }
    }

    public static void main(String[] args) {
        AtomicExample demo = new AtomicExample();
        Thread[] threads = new Thread[2];
        for (int j = 0; j < 2; j++) {
            threads[j] = new Thread(() -> {
                for (int k = 0; k < 1000; k++) {
                    demo.incr();
                }
            });
            threads[j].start();
        }

        try {
            // 等待两个线程执行结束
            threads[0].join();
            threads[1].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(demo.i);
    }
}
