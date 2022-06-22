package com.example.concurrency.queue;

public class QueueDemo {

    public static void main(String[] args) {

        MyBlockQueue q = new MyBlockQueue(10);
        new Thread(()->{
            while (true){
                q.add(Math.random());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
        new Thread(()->{
            while (true){
                System.out.println(q.poll());
            }

        }).start();
    }

}
