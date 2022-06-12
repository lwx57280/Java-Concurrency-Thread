package com.example.thread.requestdemo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public abstract class AbstractRequest extends Thread {

    protected RequestProcessor nextProcessor;

    protected BlockingQueue<Request> requests = new LinkedBlockingDeque<>();

    @Override
    public void run() {
        while (true) {
            try {

                // 异步进行请求处理
                Request request = requests.take();
                System.out.println("AbstractRequest:" + request);
                if (null != nextProcessor) {
                    nextProcessor.processorRequest(request);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
