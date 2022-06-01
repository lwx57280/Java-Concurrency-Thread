package com.concurrent.cacheline;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VolatileTest {
    //循环的终止条件，加volatile会影响运行结果

    private static volatile boolean initFlag = false;
    private static int counter = 0;


    public static void refresh() {
        log.info("refresh data.......");
        initFlag = true;
        log.info("refresh data success.......");
    }

    public static void main(String[] args) {
        // 线程A负责循环，直至收到终止的命令才会 停下来
        Thread threadA = new Thread(() -> {
            while (!initFlag) {
                counter++;
            }
            log.info("线程：" + Thread.currentThread().getName()+"当前线程嗅探到initFlag的状态的改变");

        },"threadA");
        threadA.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // B线程负责通知A 终止循环
        Thread threadB = new Thread(() -> {
            refresh();
        },"threadB");
        threadB.start();
    }
}
