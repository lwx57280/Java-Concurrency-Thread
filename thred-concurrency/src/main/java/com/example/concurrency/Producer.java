package com.example.concurrency;

import java.util.Queue;

public class Producer implements Runnable{

    private Queue<String> bags;

    private int maxSize;

    public Producer(Queue<String> bags, int maxSize) {
        this.bags = bags;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        int i=0;
        while (true) {
            i++;
            synchronized (bags) {   // 抢占锁
                if (bags.size() == maxSize) {
                    System.out.println("bags 满了");
                    try {
                        bags.wait();    // 满了，阻塞当前线程并且释放proudcer抢到的锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("生产者生成："+i);
                bags.add("bag" + i); // 生成了bag
                bags.notify();      // 表示当前已经生产了数据，提示消费者可以消费

            }
            // 同步代码块执行结束
        }
    }
}
