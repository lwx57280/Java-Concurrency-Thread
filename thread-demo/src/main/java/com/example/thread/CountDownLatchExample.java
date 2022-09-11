package com.example.thread;

import java.util.concurrent.CountDownLatch;
/**
 * CountDownLatch 实现原理
 * @author:         cong zhi
 * @createDate:     2022/9/11 16:34
 * @updateUser:     cong zhi
 * @updateDate:     2022/9/11 16:34
 * @updateRemark:   修改内容
 * @version:        v1.0
 */
public class CountDownLatchExample {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        DevTeam orderTeam = new DevTeam("orderTeam", countDownLatch);
        DevTeam accountTeam = new DevTeam("accountTeam", countDownLatch);
        orderTeam.start();
        accountTeam.start();

        countDownLatch.await(); // 等待开发团队完成代码开发
        QATeam qaTeam = new QATeam("QATeam");
        qaTeam.start();

    }

    static class QATeam extends Thread {
        public QATeam(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ":开始测试");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":测试结束");
        }
    }

    static class DevTeam extends Thread {

        private CountDownLatch countDownLatch;

        public DevTeam(String name, CountDownLatch countDownLatch) {
            super(name);
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {

            System.out.println(Thread.currentThread().getName() + ":代码开发中");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":代码开发完毕");
            countDownLatch.countDown();
        }
    }

}
