package com.example.thread.count;

public class StartupMain {
    public static void main(String[] args) {
        try {
            ApplicationStartup.checkExternalService();
        } catch (InterruptedException e) {

        }
        System.out.println("服务启动成功");
    }
}
