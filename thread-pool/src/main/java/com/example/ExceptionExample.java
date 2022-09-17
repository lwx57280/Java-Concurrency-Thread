package com.example;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
/**
 * @description:    异常处理
 * @author:         cong zhi
 * @createDate:     2022/6/27 17:29
 * @updateUser:     cong zhi
 * @updateDate:     2022/6/27 17:29
 * @updateRemark:   修改内容
 * @version:        v1.0
 */
public class ExceptionExample {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture cf = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("Occur Exception");
        }).exceptionally(e -> {
            System.out.println(e);
            return "Exceptionally";
        });
//                .handleAsync((r,th)->{
//            return th != null ? "出现异常" : r;
//        });
        System.out.println(cf.get());
//
//        cf.whenComplete((rs, th) -> {
//            if (th != null) {
//                System.out.println("前置任务出现异常");
//            }else {
//                System.out.println("前置任务正常执行：" + rs);
//            }
//        });
    }
}
