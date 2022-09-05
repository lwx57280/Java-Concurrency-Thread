package com.example;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @description: Completable 案例
 * @author: cong zhi
 * @createDate: 2022/6/27 16:50
 * @updateUser: cong zhi
 * @updateDate: 2022/6/27 16:50
 * @updateRemark: 修改内容
 * @version: v1.0
 */
public class CompletableExample {

    static class ClientThread implements Runnable {
        private CompletableFuture completableFuture;

        public ClientThread(CompletableFuture completableFuture) {
            this.completableFuture = completableFuture;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + ":" + completableFuture.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        CompletableFuture cf = new CompletableFuture();
        new Thread(new ClientThread(cf), "t1").start();
        new Thread(new ClientThread(cf), "t2").start();
        // 执行逻辑
        // normal
        cf.complete("Finish");
        // exception
//        cf.completeExceptionally(e);
    }
}
