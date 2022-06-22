package com.example.concurrency.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Wife implements Runnable {
    private Lock key;
    private Lock car;
    private Condition keyCondition;

    public Wife(Lock key, Lock car,Condition keyCondition) {
        this.key = key;
        this.car = car;
        this.keyCondition = keyCondition;
    }

    @Override
    public void run() {
        key.lock();
        try{
            System.out.println(Thread.currentThread().getName() + "获取钥匙之后的执行操作");
            try {
                Thread.sleep(1000);
                System.out.println("拿到钥匙的先给拿到车子的人");
                keyCondition.await();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            car.lock();
            try{
                System.out.println(Thread.currentThread().getName() + "获取车子之后执行操作");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                car.unlock();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            key.unlock();
        }

        System.out.println("wife开了一次车子");
    }
}
