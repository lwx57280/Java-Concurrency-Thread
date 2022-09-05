package com.learn.example.threadexample;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
/**
 * condition应用
 * @author:         cong zhi
 * @createDate:     2022/9/5 15:56
 * @updateUser:     cong zhi
 * @updateDate:     2022/9/5 15:56
 * @updateRemark:   修改内容
 * @version:        v1.0
 */
public class ConditionDemoWait implements Runnable{

    private Lock lock;

    private Condition condition;

    public ConditionDemoWait(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        System.out.println("begin-CoditionDemoWait");
        try {
            lock.lock();
            ;
            condition.await();
            System.out.println("end -CoditionDemoWait");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
