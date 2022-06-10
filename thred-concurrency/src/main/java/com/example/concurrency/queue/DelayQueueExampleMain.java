package com.example.concurrency.queue;

import java.util.concurrent.DelayQueue;

/**
 * 延时执行的队列
 */
public class DelayQueueExampleMain {

    private static DelayQueue<DelayQueueExampleTask> delayQueue=new DelayQueue();

    public static void main(String[] args) {
        delayQueue.offer(new DelayQueueExampleTask("1001", 1000));
        delayQueue.offer(new DelayQueueExampleTask("1002", 5000));
        delayQueue.offer(new DelayQueueExampleTask("1003", 3000));
        delayQueue.offer(new DelayQueueExampleTask("1004", 4000));
        delayQueue.offer(new DelayQueueExampleTask("1005", 2000));
        delayQueue.offer(new DelayQueueExampleTask("1006", 8000));
        delayQueue.offer(new DelayQueueExampleTask("1007", 3000));
        while (true) {

            try {
                DelayQueueExampleTask task = delayQueue.take();
                System.out.println(task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
