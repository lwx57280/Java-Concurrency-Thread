package com.example.thread;

public class InterruptExample  implements Runnable{


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new InterruptExample());
        t1.start();
        Thread.sleep(1000);
        // main线程来决定t1线程生死
        t1.interrupt(); // 发送一个中断信号，中断标记为true

    }
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { // 中断标记为false
                e.printStackTrace();
//                Thread.currentThread().interrupt();
            }
            System.out.println(Thread.currentThread().getName()+"---");
        }
    }
}
