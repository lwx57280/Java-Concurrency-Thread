package com.example.concurrency.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * @description:    condition
 * @author:         cong zhi
 * @createDate:     2022/6/4 16:03
 * @updateUser:     cong zhi
 * @updateDate:     2022/6/4 16:03
 * @updateRemark:   修改内容
 * @version:        v1.0
 */
public class ConditionExample {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        ConditionDemoWait cdw = new ConditionDemoWait(lock, condition);
        ConditionDemoNotify cdn = new ConditionDemoNotify(lock, condition);
        new Thread(cdw).start();
        new Thread(cdn).start();
    }

}
