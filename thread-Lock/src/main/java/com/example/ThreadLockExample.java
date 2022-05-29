package com.example;
/**
 * ThreadLocal 线程隔离
 * @Author:         cong zhi
 * @CreateDate:     2021/6/20 18:24
 * @UpdateUser:     cong zhi
 * @UpdateDate:     2021/6/20 18:24
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public class ThreadLockExample {
    // 希望每个线程获得num都是0
//    public static int num = 0;

    static ThreadLocal<Integer> local = new ThreadLocal<Integer>() {

        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public static void main(String[] args) {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                int num = local.get().intValue();
                local.set(num += 5);
                System.out.println(Thread.currentThread().getName() + " " + local.get());
            });
        }
        for (int i = 0; i < 5; i++) {
            threads[i].start();
        }
    }

}
