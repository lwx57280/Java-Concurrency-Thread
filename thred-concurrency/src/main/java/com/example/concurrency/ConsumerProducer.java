package com.example.concurrency;

import java.util.LinkedList;
import java.util.Queue;
/**
 * @description:    生产者消费者
 * @author:         cong zhi
 * @createDate:     2022/6/4 15:46
 * @updateUser:     cong zhi
 * @updateDate:     2022/6/4 15:46
 * @updateRemark:   修改内容
 * @version:        v1.0
 */
public class ConsumerProducer {

    public static void main(String[] args) throws InterruptedException {
        Queue<String> strings = new LinkedList<>();
        Producer producer = new Producer(strings, 10);
        Consumer consumer = new Consumer(strings, 10);
        new Thread(producer).start();
        Thread.sleep(100);
        new Thread(consumer).start();

    }
}
