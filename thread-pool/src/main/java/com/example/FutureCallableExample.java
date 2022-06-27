package com.example;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @description: Future/Callable
 * @author: cong zhi
 * @createDate: 2022/6/27 14:56
 * @updateUser: cong zhi
 * @updateDate: 2022/6/27 14:56
 * @updateRemark: 修改内容
 * @version: v1.0
 */
public class FutureCallableExample {

    static class CalculationCallable implements Callable<Integer> {
        private int x;
        private int y;

        public CalculationCallable(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println("begin call:" + new Date());
            // 模拟任务执行的耗时
            TimeUnit.SECONDS.sleep(2);

            return x + y;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CalculationCallable calculationCallable = new CalculationCallable(1, 2);
        FutureTask<Integer> futureTask = new FutureTask<>(calculationCallable);
        new Thread(futureTask).start(); // 本质上还是一个线程
        System.out.println("begin execute futureTask:" + new Date());
        Integer rs = futureTask.get(); // 阻塞方法
        System.out.println("result:" + rs + "");
        System.out.println("end execute futureTask:"+new Date());
    }
}
