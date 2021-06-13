package com.example.concurrency.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 队列实现案例
 */
public class ConditionBlockedQueueExample {

    /**
     * 表示阻塞队列中的容器
     */
    private List<String> items;

    /**
     * 元素个数
     */
    private volatile int size;

    /**
     * 数组的容量
     */
    private volatile int count;

    private Lock lock = new ReentrantLock();
    // 让take  方法阻塞 ->wait/notify
    private final Condition notEmpty = lock.newCondition();
    // 让add 方法阻塞
    private final Condition notFull = lock.newCondition();


    public ConditionBlockedQueueExample(int count) {
        this.count = count;
        this.items = new ArrayList<>();
    }

    /**
     * 添加元素
     */
    public void put(String item) {
        lock.lock();
        try {
            if (size >= count) {
                System.out.println("队列满了，需要等一会儿！");
                notFull.await();
            }
            ++size;
            items.add(item);
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 取出元素
     *
     * @return
     */
    public String take() {
        lock.lock();
        try {
            if (size == 0) {
                System.out.println("阻塞队列空了，需要等一会儿！");
                notEmpty.await();
            }
            --size;
            String item = items.remove(0);
            notFull.signal();
            return item;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionBlockedQueueExample cbqe = new ConditionBlockedQueueExample(10);
        // 生产者线程
        Thread t1 = new Thread(() -> {
            Random random = new Random();
            try {

                for (int i = 0; i < 1000; i++) {
                    String item = "item" + i;
                    cbqe.put(item);
                    System.out.println("生产一个元素:" + item);
                    Thread.sleep(random.nextInt(1000));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();

        Thread.sleep(100);
        // 消费者线程
        Thread t2 = new Thread(() -> {
            Random random = new Random();
            try {
                for (; ; ) {
                    String item = cbqe.take();
                    System.out.println("消费者线程消费一个元素:" + item);
                    Thread.sleep(random.nextInt(1000));

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();
    }


}
