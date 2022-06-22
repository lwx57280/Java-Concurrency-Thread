package com.example.concurrency.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Husband implements Runnable {
    private Lock key;
    private Lock car;
    private Condition keyCondition;

    public Husband(Lock key, Lock car,Condition keyCondition) {
        this.key = key;
        this.car = car;
        this.keyCondition = keyCondition;
    }

    @Override
    public void run() {
        car.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "获取车子之后的执行操作");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            key.lock();
            try{
                System.out.println(Thread.currentThread().getName() + "获取钥匙之后执行操作");
                try {
                    Thread.sleep(1000);
                    keyCondition.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                key.unlock();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            car.unlock();
        }
        System.out.println("husband开了一次车子");
    }
}
