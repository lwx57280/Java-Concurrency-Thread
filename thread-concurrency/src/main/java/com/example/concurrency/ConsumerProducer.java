package com.example.concurrency;



import java.util.LinkedList;
import java.util.Queue;

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
