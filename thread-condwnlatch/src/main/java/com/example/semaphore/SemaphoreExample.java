package com.example.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量
 *
 */
public class SemaphoreExample {

    public static void main(String[] args) {
        // 限制资源访问并发数
        Semaphore semaphore = new Semaphore(10);
        for (int i = 0; i < 20; i++) {
            new Car(i, semaphore).start();
        }
    }


    static class Car extends Thread{

        private int num;

        private Semaphore semaphore;

        public Car(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                // 获得一个令牌
                semaphore.acquire();
                System.out.println("第 "+num+" 辆车抢到一个车位");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("第 "+num+"走咯~");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release(); // 释放
            }
        }
    }
}
