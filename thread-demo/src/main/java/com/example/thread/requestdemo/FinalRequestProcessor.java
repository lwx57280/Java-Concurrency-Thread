package com.example.thread.requestdemo;

public class FinalRequestProcessor  implements RequestProcessor {

    protected RequestProcessor nextProcessor;


    @Override
    public void processorRequest(Request request) {
        System.out.println("FinalRequestProcessor:"+request);
    }
}
