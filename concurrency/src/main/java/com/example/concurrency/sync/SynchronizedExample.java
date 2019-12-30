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
public class SynchronizedExample {

    public static void main(String[] args) {
        SynchronizedExample synchronizedExample = new SynchronizedExample();

        ExecutorService executorService = Executors.newCachedThreadPool();
        //executorService.execute(() -> synchronizedExample.test2());


       // executorService.execute(() -> synchronizedExample.notStaticTest3());

        executorService.execute(() -> synchronizedExample.test1(1));
        executorService.execute(() -> synchronizedExample.test1(2));
    }

    /**
     * 修饰一个代码块
     *
     * @return
     * @throws
     * @author li cong zhi
     * @date 2019/12/30 15:09
     */
    private void test1(int j) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 {} -{}", j, i);
            }
        }
    }

    /**
     * 修饰一个代码块
     *
     * @return
     * @throws
     * @author li cong zhi
     * @date 2019/12/30 15:09
     */
    private void test3() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test3 -{}", i);
            }
        }
    }

    /**
     * 修饰方法
     *
     * @return
     * @throws
     * @author li cong zhi
     * @date 2019/12/30 15:10
     */
    private synchronized void test2() {
        for (int i = 0; i < 10; i++) {
            log.info("test2 -{}", i);
        }
    }

}
