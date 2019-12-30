package com.example.concurrency.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程 Synchronized同步锁
 *
 * @Author: li cong zhi
 * @CreateDate: 2019/12/30 15:08
 * @UpdateUser: li cong zhi
 * @UpdateDate: 2019/12/30 15:08
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Slf4j
public class SynchronizedExample2 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //executorService.execute(() -> synchronizedExample.test2());


       // executorService.execute(() -> synchronizedExample.notStaticTest3());

        // 静态方法随着类加载优先执行
        executorService.execute(() -> SynchronizedExample2.staticTest1(1));
        executorService.execute(() -> SynchronizedExample2.staticTest1(2));
    }

    /**
     * 修饰一个静态代码块
     *
     * @return
     * @throws
     * @author li cong zhi
     * @date 2019/12/30 15:09
     */
    private static void staticTest1(int j) {
        synchronized (SynchronizedExample2.class) {
            for (int i = 0; i < 10; i++) {
                log.info("staticTest1 {} -{}", j, i);
            }
        }
    }

    /**
     * 修饰一个非静态代码块
     *
     * @return
     * @throws
     * @author li cong zhi
     * @date 2019/12/30 15:09
     */
    private void notStaticTest3() {
        synchronized (SynchronizedExample2.class) {
            for (int i = 0; i < 10; i++) {
                log.info("notStaticTest3 -{}", i);
            }
        }
    }

    /**
     * 修饰一个静态方法
     * 这个类所有对象，同一个时间只有一个线程可以执行
     * @return
     * @throws
     * @author li cong zhi
     * @date 2019/12/30 15:10
     */
    private static synchronized void test2() {
        for (int i = 0; i < 10; i++) {
            log.info("test2 -{}", i);
        }
    }

}
