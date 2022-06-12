package com.example.thread.requestdemo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class PrintProcessor extends Thread implements RequestProcessor {


    protected RequestProcessor nextProcessor;

    protected BlockingQueue<Request> requests = new LinkedBlockingDeque<>();

    public PrintProcessor(RequestProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    @Override
    public void processorRequest(Request request) {
        requests.add(request);
    }

    @Override
    public void run() {
        while (true) {
            try {

                // 异步进行请求处理
                Request request = requests.take();
                System.out.println("PrintProcessor:" + request);
                if (null != nextProcessor) {
                    nextProcessor.processorRequest(request);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
